package or.dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: key=对象转换工具(数据复制)
 * @Author
 * @Date 2021/1/14
 * @Version 1.0
 */
public class DozerUtil {

    private static Mapper mapper = new DozerBeanMapper();

    public static <T> List<T> transforList(List<?> source, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (source == null)
            return list;

        for (Object entity : source) {
            T t = transfor(entity, clazz);
            list.add(t);
        }
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

    public static void main(String[] args) {
        Ninja ninja = new Ninja();
        ninja.setNinjaId(7L);
        ninja.setNation("木叶村");
        CopyNinja copyNinja = transfor(ninja, CopyNinja.class);
        System.out.println(copyNinja);

        List<Ninja> ninjaList = new ArrayList<>();
        ninjaList.add(ninja);

        ninja = new Ninja();
        ninja.setNinjaId(9L);
        ninja.setNation("砂隐村");

        ninjaList.add(ninja);
        List<CopyNinja> copyNinjaList = transforList(ninjaList, CopyNinja.class);
        System.out.println(copyNinjaList);

        CopyNinja cn = new CopyNinja();
        transfor(ninja, cn);
        System.out.println(cn);
    }
}

/*
// Class org.dozer.util.ReflectionUtils can not access a member of class or.dozer.CopyNinja with modifiers "public"
@Data
class CopyNinja {
    private Long ninjaId;
    private String nation;
    private String realNation;
}*/
