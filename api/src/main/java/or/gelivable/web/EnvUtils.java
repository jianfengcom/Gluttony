package or.gelivable.web;

/*
    ##: geli
 */
public class EnvUtils {
    private static ThreadLocal<Env> threadLocal = new ThreadLocal<Env>() {
        @Override
        protected Env initialValue() {
            return new Env();
        }
    };

    public EnvUtils() {
    }

    public static Env getEnv() {
        return (Env) threadLocal.get();
    }

    public static void removeEnv() {
        threadLocal.remove();
    }
}
