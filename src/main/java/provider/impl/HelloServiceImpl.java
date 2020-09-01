package provider.impl;

import provider.api.HelloService;

/**
 * Created by Yuk on 2018/12/
 */
public class HelloServiceImpl implements HelloService {

    public String sayHello(String username) {
        System.out.println("Hello");
        return "Hello:" + username;
    }

    public String addStr(String a, String b) {
        System.out.println("addStr");
        return a + "-" + b;
    }

    public Integer addInteger(Integer a, Integer b) {
        System.out.println("addInteger");
        return a + b;
    }
}
