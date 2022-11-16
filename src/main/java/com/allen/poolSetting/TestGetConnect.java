package com.allen.poolSetting;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: allen
 * @Date: 2022/9/26 21:15
 * @Description: 连接池测试案例
 **/
public class TestGetConnect {

    private static final Logger logger = LoggerFactory.getLogger(TestGetConnect.class);
    private final HikariDataSource dataSource;

    public TestGetConnect() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://10.10.220.15:3306/PerfCase?characterEncoding=utf-8&useSSL=false");
        config.setUsername("root");
        config.setPassword("Perfma888.");
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(90000);
        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        TestGetConnect testGetConnect = new TestGetConnect();
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        int i = 0;
        while (i < 10000) {
            executorService.submit(() -> {
                try {
                    long starTime = System.currentTimeMillis();
                    Connection conn = testGetConnect.getConnection();
                    Thread.sleep(1000);
                    conn.close();
                    logger.info("线程：「{}」查询结束，耗时：{}", Thread.currentThread().getName(), (System.currentTimeMillis() - starTime));
                } catch (SQLException | InterruptedException throwables) {
                    throwables.printStackTrace();
                }
            });
            i++;
        }
        executorService.shutdown();
    }
}
