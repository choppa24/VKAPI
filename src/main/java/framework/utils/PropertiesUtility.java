package framework.utils;

import enums.Paths;
import enums.Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {
    private static Properties property = new Properties();
    private static FileInputStream fis;
    private PropertiesUtility() {
    }

    public static void setUtilityFile(String file) {
        try {
            LoggerUtility.info("set File");
            fis = new FileInputStream(Paths.RESOURCES.toString() + file);
            property.load(fis);
        } catch (IOException e) {
            LoggerUtility.error("ERROR: config file is missing");
            throw new RuntimeException();
        }
    }

    public static String getStringValue(String file, String key) {
        setUtilityFile(file);
        LoggerUtility.info("Getting a text value '" + key + "' from config file ");
        return property.getProperty(key);
    }

    public static int getIntValue(String file, String key) {
        setUtilityFile(file);
        LoggerUtility.info("Getting an integer value '" + key + "' from config file ");
        return Integer.parseInt(property.getProperty(key));
    }
}