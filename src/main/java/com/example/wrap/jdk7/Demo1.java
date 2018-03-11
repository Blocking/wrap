package com.example.wrap.jdk7;

/**
 * @author 12232
 * @date 2017/11/21
 */
public class Demo1 {
    public static void main(String[] args) {
        //JDK7开始，终于可以用二进制来表示整数（byte,short,int和long）。使用二进制字面量的好处是，可以使代码更容易被理解（没看出来）。
        // 语法非常简单，只要在二进制数值面前加0b或者0B
        int ib = 0b0010;
        long lb = 0b0010L;
        System.out.println(ib);
        System.out.println(lb);
        //2.数字字面量可以出现下划线
        int ii = 10_000_000;
        System.out.println(ii);
    }
}
