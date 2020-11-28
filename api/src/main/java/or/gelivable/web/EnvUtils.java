package or.gelivable.web;

/**
 * @Description:
 * @Author
 * @Date 2020/11/28
 * @Version 1.0
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
