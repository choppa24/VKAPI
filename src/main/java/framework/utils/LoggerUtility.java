package framework.utils;

import org.apache.log4j.Logger;

public class LoggerUtility {
    private static final Logger log = Logger.getLogger(LoggerUtility.class);

    public static Logger getLogger(){
        return log;
    }

    public static void info(String message){
        log.info(message);
    }

    public static void error(String message){
        log.error(message);
    }
}
