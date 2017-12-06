package com.example.wrap.DesignPatterns.one;

/**
 * Created by 12232 on 2017/10/11.
 * 计谋 孙夫人断后
 */
public class BlockEnemy implements IStrategy {
    @Override
    public void opreate() {
        System.out.println("孙夫人断后，玄德再过一劫");
    }
}
