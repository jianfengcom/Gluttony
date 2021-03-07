package or.dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/*
    ##:拷贝
 */
public class DozerUtil {

    private static Mapper mapper = new DozerBeanMapper();

    public static <T> List<T> transforList(List<?> source, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (source == null)
            return list;

        source.forEach(entity -> {
            T t = transfor(entity, clazz);
            list.add(t);
        });
        /*for (Object entity : source) {
            T t = transfor(entity, clazz);
            list.add(t);
        }*/
        return list;
    }

    public static <T> T transfor(Object source, Class<T> target) {
        if (source == null)
            return null;

        return mapper.map(source, target);
    }

    public static void transfor(Object source, Object target) {
        if (source == null || target == null)
            return;

        mapper.map(source, target);
    }

}


/*
    如果用下面的CopyNinja类, 会报错

    Caused by: java.lang.IllegalAccessException:
    Class org.dozer.util.ReflectionUtils can not access a member of class or.dozer.CopyNinja with modifiers "public"
    */

/*@Data
class CopyNinja {
    private Long ninjaId;
    private String nation;
    private String realNation;
}*/
