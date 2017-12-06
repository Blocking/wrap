package com.example.wrap.DesignPatterns.one;

/**
 * Created by 12232 on 2017/10/11.
 * 计谋  找乔国舅救人
 */
public class BackDoor implements IStrategy {
    @Override
    public void opreate() {
        System.out.println("乔国救刘备，玄德逃过一劫");
    }
}
