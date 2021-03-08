package jav.lang;

import lombok.Data;

/*
    ##: 函数式接口
 */
public class FuncIntf {

    /*public static void main(String[] args) {
        callNothing(() -> {
            System.out.println("NOTHING 做一些快乐的事");
        });
    }

    public static void callNothing(Comsumer comsumer) {
        comsumer.nothing();
    }

    // 定义"函数式接口"时,为防止发生定义错误.可以使用@FunctionalInterface注解,强制按照"函数式接口"的语法检查,如果语法错误.编译器报错.
    @FunctionalInterface // 注解非强制性
    public interface Comsumer {
        void nothing();
    }*/

    public static void main(String[] args) {
        callApply(666, (brand, brandExpand) -> {
            String ret = brand.getBrandId() + "--" + brand.getName() + "--" + brandExpand.getWebsite();
            System.out.println(ret);
        }); // 实现
    }

    public static void callApply(int id, Comsumer<Brand, BrandExpand> comsumer) {
        Brand brand = new Brand();
        brand.setBrandId(9527L);
        brand.setName("白度");

        BrandExpand brandExpand = new BrandExpand();
        brandExpand.setWebsite("https://www.baidu.com/");

        comsumer.apply(brand, brandExpand); // 调用
    }

    public interface Comsumer<A, B> {
        void apply(A a, B b);
    }

    /*
        使用自定义静态方法替代lambda
        使用类库静态方法替代lambda
        常规

        --补充
        Supplier
        生产者接口,不接受参数,有返回值

        Consumer
        消费者接口,只接受参数,无返回值

        --下面的有时间可以尝试
        使用super父类方法替代Lambda
        使用this本类方法替代Lambda
        类的构造器 FuncIntf::new
        数组构造器 int[]::new
     */
    /*public static void main(String[] args) {
        String[] sArr = {"BB", "我好挂住你啊"};
        callApply(FuncIntf::applyImpl, sArr);
        callApply(Arrays::toString, sArr);
        callApply(new StaticMethodComsumer() {
            @Override
            public String apply(String[] sArr) {
                return null;
            }
        }, sArr);
    }

    public static void callApply(StaticMethodComsumer comsumer, String[] sArr) {
        String ret = comsumer.apply(sArr);
        System.out.println(ret);
    }
    public static String applyImpl(String[] sArr) {
        StringBuilder sb = new StringBuilder();
        for (String s : sArr) {
            sb.append(s + ", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    public interface StaticMethodComsumer{
        String apply(String[] sArr);
    }*/
}

@Data
class Brand {
    private Long brandId;
    private String name;
}

@Data
class BrandExpand {
    private String website;
}
