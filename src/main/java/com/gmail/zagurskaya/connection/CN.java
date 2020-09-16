package com.gmail.zagurskaya.connection;

public interface CN {
//    ??????? почему!!!!!!!!!!!!
    String URL =
//            "jdbc:mysql://127.0.0.1:2016/" +
            "jdbc:mysql://127.0.0.1:2016/zagurskaya" +
                    "?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String USER = "root";
    String PASSWORD = "";

    String URLCHEMA =
            "jdbc:mysql://127.0.0.1:2016/" +
                    "?useUnicode=true&characterEncoding=UTF-8";
   }
