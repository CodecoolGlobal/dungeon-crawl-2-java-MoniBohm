package com.codecool.dungeoncrawl.util;

import java.sql.Date;

public class MiscUtilHelper {

    public static Date getDate() {
        long millis=System.currentTimeMillis();
        return new Date(millis);
    }

}
