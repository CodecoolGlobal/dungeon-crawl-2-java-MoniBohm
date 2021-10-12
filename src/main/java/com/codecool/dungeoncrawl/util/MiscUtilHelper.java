package com.codecool.dungeoncrawl.util;

import java.sql.Date;
import java.sql.Timestamp;

public class MiscUtilHelper {

    public static Timestamp getDate() {
        long millis=System.currentTimeMillis();
        return new Timestamp(new Date(millis).getTime());
    }

}
