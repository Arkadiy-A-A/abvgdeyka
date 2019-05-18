import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Created by OUT-Akopyan-SR on 07.05.2019.
 */
public class Log {
    /**
     * Переменная для логирования
     */
    public static Logger log = Logger.getLogger(Log.class);

    static {
        log.setLevel(Level.DEBUG);
    }
}

