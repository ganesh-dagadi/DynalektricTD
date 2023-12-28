package com.dynalektric.constants;

public class SystemConstants {
    public final static String CURR_DIR = System.getProperty("user.dir");
    public final static String INSTALL_DIR = CURR_DIR + "/dist/";
    public final static String DATABASE_DIR = INSTALL_DIR + "/data/";
    public final static String GENERAL_FILE = DATABASE_DIR + "general.json";
}
