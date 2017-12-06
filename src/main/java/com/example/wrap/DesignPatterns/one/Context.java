package com.example.wrap.DesignPatterns.one;

/**
 * Created by 12232 on 2017/10/11.
 * 锦囊
 */
public class Context {
    private IStrategy iStrategy;
    public Context(IStrategy iStrategy){
        this.iStrategy = iStrategy;
    }
    //执行锦囊妙计
    public void operate(){
        if (iStrategy!=null){
            iStrategy.opreate();
        }else{
            System.out.println("无计可施");
        }
    }
}
