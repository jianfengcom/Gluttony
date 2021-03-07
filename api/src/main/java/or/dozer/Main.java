package or.dozer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Ninja ninja = new Ninja();
        ninja.setNinjaId(7L);
        ninja.setNation("木叶村");
        CopyNinja copyNinja = DozerUtil.transfor(ninja, CopyNinja.class);
        System.out.println("对象拷贝=" + copyNinja);

        List<Ninja> ninjaList = new ArrayList<>();
        ninjaList.add(ninja);

        ninja = new Ninja();
        ninja.setNinjaId(9L);
        ninja.setNation("砂隐村");

        ninjaList.add(ninja);
        List<CopyNinja> copyNinjaList = DozerUtil.transforList(ninjaList, CopyNinja.class);
        System.out.println("对象列表拷贝=" + copyNinjaList);

        CopyNinja cn = new CopyNinja();
        DozerUtil.transfor(ninja, cn);
        System.out.println("对象变量拷贝=" + cn);
    }
}
