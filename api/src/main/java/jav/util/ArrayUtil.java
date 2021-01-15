package jav.util;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: key=Arrays
 * @Author
 * @Date 2021/1/14
 * @Version 1.0
 */
public class ArrayUtil {
    public static void main(String[] args) {
        String[] resource = {"first", "nba", null};
        System.out.println(Arrays.toString(resource));

        Arrays.sort(resource);
        System.out.println(Arrays.toString(resource));

        Arrays.sort(resource, stringComparator());
        System.out.println(Arrays.toString(resource));
    }

    public static Comparator<String> stringComparator() {
        return (a, b) -> {
            return b.length() - a.length();
        };
    }

    /*
        key=判断数组是否包含目标元素
     */
    public static boolean contains(int[] val, int target) {
        // 排序
        Arrays.sort(val);

        int i = Arrays.binarySearch(val, target);
        if(i > 0) {
            return true;
        }else {
            return false;
        }
    }
}
