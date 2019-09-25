package com.sinoteif.py.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2019/9/25.
 */
@Component
@ConfigurationProperties(prefix = "data.env")
public class EnvConfig {
    private String envName;
    private String redisServer;
    private String mysqlServer;

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public String getRedisServer() {
        return redisServer;
    }

    public void setRedisServer(String redisServer) {
        this.redisServer = redisServer;
    }

    public String getMysqlServer() {
        return mysqlServer;
    }

    public void setMysqlServer(String mysqlServer) {
        this.mysqlServer = mysqlServer;
    }
}
