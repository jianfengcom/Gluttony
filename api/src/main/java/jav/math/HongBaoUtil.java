package jav.math;

import java.math.BigDecimal;

/*
    ##: wait pdd-红包算法
 */
public class HongBaoUtil {
    /**
     * 随机200以内的随机数
     * 根据差值再次随机
     * 最终差值小于1的时候固定返回0.01
     */
    public static BigDecimal getOneRandomNumber(BigDecimal bd) {
        Double number = bd.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        if (number >= 200) { // 判断数值是否≥200，是则是第一次随机
            // 150 <= 随机数 < 190
            double db = (Math.random() * (190 - 150)) + 150;
            BigDecimal bg = new BigDecimal(db);
            return bg.setScale(2, BigDecimal.ROUND_DOWN);
        } else if (number >= 1) { // 判断数值是否≥1，是则是第N次随机
            return getOneRandomNumber(BigDecimal.valueOf(0.01), bd);
        } else { // 上述条件都不满足，则让之后每次都返回0.01
            return new BigDecimal(0.01).setScale(2, BigDecimal.ROUND_DOWN);
        }
    }

    public static BigDecimal getOneRandomNumber(BigDecimal minBig, BigDecimal maxBig) {
        // 随机一个数，数值经度保留小数点后两位
        double db = (Math.random() * (maxBig.subtract(minBig).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()) + minBig.setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
        BigDecimal bg = new BigDecimal(db);
        return bg.setScale(2, BigDecimal.ROUND_DOWN);
    }

    public static void main(String[] args) {
        HongBaoUtil hongBaoUtils = new HongBaoUtil();
        BigDecimal number = new BigDecimal(200);
        int i = 0;
        while (number.doubleValue() > 0) {
            i++;
            BigDecimal oneRandomNumber = hongBaoUtils.getOneRandomNumber(number);
            System.err.println("获取红包 ==> " + oneRandomNumber);
            // System.out.println("剩余金额 ==> " + number.setScale(2, BigDecimal.ROUND_DOWN));
            number = number.subtract(oneRandomNumber);
        }
        System.out.println("总共 ==> " + i);
    }

    public static void main2(String[] args) {
        BigDecimal number1 = new BigDecimal(0.1);
        BigDecimal number2 = new BigDecimal(0.2);
        BigDecimal sum = new BigDecimal(0.3);
        System.out.println(number1);

        System.out.println(number1.add(number2).compareTo(sum));
        System.out.println(0.1 + 0.2 > 0.3);

        // key=double
        // 原_反_补码 精度
        // double 不能完全正确地表示0.1, 打印出来的是0.1, 实际不是
        // 比较大小
    }
}