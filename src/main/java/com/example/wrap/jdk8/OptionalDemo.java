package com.example.wrap.jdk8;

import com.example.wrap.jdk8.entity.Address;
import com.example.wrap.jdk8.entity.User;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by 12232 on 2017/12/9.
 */
public class OptionalDemo {
    @Test
    public void demo1(){
        //创建空的Optional
        Optional<User> userEmpty = Optional.empty();

        User user = new User("tom","123456");
        Optional<User> userOptional = Optional.of(user);
        //If a value is present, invoke the specified consumer with the value, otherwise do nothing
        //如果值存在 那么调用提供的行为，否则什么都不做
        userOptional.ifPresent(user1 -> {
            System.out.println(user.getUserName());
        });
    }

    @Test
    public void demo2(){
        //以下一段代码会造成 nullPointerException
        User userNull = null;
        //
        Optional<User> userNullOptional = Optional.of(userNull); //of(@NotNull T value)  不能给null值
        userNullOptional.ifPresent(user -> {
            System.out.println("Name:::"+user.getUserName());
        });
        //ofNullable() 方法不会造成空指针异常那个
        Optional<User> user = Optional.ofNullable(userNull);//Returns an Optional describing the specified value, if non-null, otherwise returns an empty Optional.
        user.ifPresent(user1 -> {
            System.out.println("Name:::"+user1.getUserName());
        });

    }
    @Test
    public void demo3(){
        User user = new User("tom","123465");
        Optional optional = Optional.of(user);
        if (optional.isPresent()) {//true if there is a value present, otherwise false
            System.out.println("发现实例对象："+optional.get());//使用get() 方法需要提前检查值是否存在 否则会抛出 NoSuchElementException.
        }else{
            System.out.println("未发现实例对象");
        }
        //ifPresent()方法允许你执行一个提供商方法 如果这个值是确实存在的
        optional.ifPresent(o -> {
            System.out.println("发现实例对象："+optional.get());
        });
    }

    /**
     * 使用orElse()方法设置默认值
     */
    @Test
    public void demo4(){
        Optional<User> finalUser = Optional.empty();
        User user = finalUser.orElse(new User("tom1","123"));
        System.out.println(user.getUserName());
    }

    /**
     * 使用orElseGet() 方法 当值为空时使用提供方法进行获取对应的值
     * 与orElse()方法不同的是 orElseGet()可以接受一个供应商方法 这样可以更灵活的获取对应的值
     */
    @Test
    public void demo5(){
        Optional<User> finalUser = Optional.empty();
        User user = finalUser.orElseGet(() -> {
           return new User("tom","password");
        });
        System.out.println(user.getPassword());

    }

    /**
     * 当Optional为空时 使用orElseThrow() 去抛异常
     */
    @Test
    public void demo6(){
        Optional<User> finalUser = Optional.empty();
        finalUser.orElseThrow(() -> new RuntimeException("User not fund "));

    }

    /**
     * Optional 的过滤方法 filter()
     */
    @Test
    public void demo7(){
        //例1
        User user1 = new User("tom","password","MALE");
        if (user1!=null && user1.getGender().equalsIgnoreCase("MALE")){
            System.out.println("111:"+user1.getGender());
        }
        //例2
        Optional<User> userOptional = Optional.empty();
        /**
         * filter() :: If a value is present, and the value matches the given predicate, return an Optional describing the value, otherwise return an empty Optional
         */
        userOptional.filter(user -> user.getGender().equalsIgnoreCase("MALE"))
                .ifPresent(user -> {
                    System.out.println(user.getGender());
                });

    }

    /**
     * map()
     */
    @Test
    public void demo8(){
        Address address = new Address("china","beijing");
        User user  = new User("tom", address);
        if(user != null){
            Address address1 = user.getAddress();
            if(address1!=null && address1.getCountry().equalsIgnoreCase("china")){
                System.out.println("1、user belong to china");
            }
        }

        Optional<User> userOptional = Optional.of(user);
        userOptional
                .map(User::getAddress)
                .filter(address1 -> address1.getCountry().equalsIgnoreCase("china"))
                .ifPresent(address1 -> {
                    System.out.println("2、user belong to china");
                });
        //拆分流程
        //1、使用map方法获取包含Address的Optional
        Optional<Address> addressOptional = userOptional.map(User::getAddress);
        //2 过滤是china的地址
        Optional<Address> addressOptional1 = addressOptional.filter(address1 -> address1.getCountry().equalsIgnoreCase("china"));
        //3、查看是否存在
        addressOptional1.ifPresent(address1 -> {
            System.out.println("3、user belong to china");
        });


    }

    /**
     * flatMap()
     */
    @Test
    public void demo9(){
        Address address = new Address("china","beijing");
        User user  = new User("tom", address);
        Optional<User> userOptional = Optional.of(user);
        Optional<Optional<Address>> addressOptional = userOptional.map(User::getOptionalAddress);
        //很明显 当getOptionalAddress()方法返回的是Optional的时候map方法会使得返回结果变的复杂 套了俩层 这明显不是我们想要的
        //所以 聪明的设计者们 还提供了flatMap方法 看下面的结果 这样就舒坦多了
        Optional<Address> addressOptional1 = userOptional.flatMap(User::getOptionalAddress);

    }



}
