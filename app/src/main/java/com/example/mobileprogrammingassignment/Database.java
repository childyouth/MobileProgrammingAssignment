package com.example.mobileprogrammingassignment;

import android.provider.BaseColumns;

public final class Database {
    public static final class DB implements BaseColumns{
        public static final String USERID = "userid";
        public static final String USERPW = "userpw";
        public static final String USERNAME = "username";
        public static final String USERPHONE = "userphone";
        public static final String USERADDR = "useraddr";
        public static final String _TABLENAME = "usertable";
        public static final String _CREATE0 = "create table if not exists " +
                _TABLENAME + "("  +
                USERID + " text , "+
                USERPW + " text not null , "+
                USERNAME + " text , "+
                USERPHONE + " text , "+
                USERADDR + " text ," +
                "primary key ( "  + USERID + "));";
    }
}
