package com.fizz.jdbc.core;

import java.io.InputStream;

public class JdbcFactory {

    private static SimpleJdbc ourInstance;

    public static SimpleJdbc getInstance(InputStream in) {
        if (ourInstance == null) {
            JdbcConf.init(in);
            ourInstance = new SimpleJdbc();
        }
        return ourInstance;
    }

}
