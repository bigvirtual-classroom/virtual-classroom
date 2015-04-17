package com.tarena.autorun.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by bjchenxx on 2015/2/3.
 */
public class Cfs {
    private Properties properties;

    public Cfs(String fileName) {
        properties = new Properties();
        try {
            properties.load(Cfs.class.getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public Integer getAsInteger(String key) {
        return Integer.valueOf(properties.getProperty(key));
    }

    public String getAsString(String key) {
        return properties.getProperty(key);
    }
}
