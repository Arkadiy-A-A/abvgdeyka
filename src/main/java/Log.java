import org.apache.log4j.Logger;

/**
 * Created by OUT-Akopyan-SR on 07.05.2019.
 */
public class Log {
    final static Logger logger = Logger.getLogger(Log.class);

    public void logMessages() {
        logger.debug("debug"); // all
        logger.info("info"); // except debug
        logger.warn("warn"); // except debug and info
        logger.error("error"); // except debug, info and warn
        logger.fatal("fatal"); // only fatal
        try {
            throw new Exception("test exception");
        } catch (Exception e) {
            logger.error("exception", e);
        }
    }
}

