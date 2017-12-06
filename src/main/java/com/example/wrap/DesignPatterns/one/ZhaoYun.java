package com.example.wrap.DesignPatterns.one;

/**
 * Created by 12232 on 2017/10/11.
 * 赵云 赵子龙 手拿锦囊妙计 计救刘备
 */
public class ZhaoYun {
    public static void main(String[] args) {
        Context context;
        context = new Context(new BackDoor());
        context.operate();
        context = new Context(new GivenGreenLight());
        context.operate();
        context = new Context(new BlockEnemy());
        context.operate();

    }
}
