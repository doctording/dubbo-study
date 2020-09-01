package consumer;

import framework.ProxyFactory;
import provider.api.HelloService;

/**
 * Created by Yuk on 2018/12/31.
 */
public class consumer {

    public static void main(String[] args) {
        // 此处模拟spring容器
        // 获取代理对象，调用方法时会执行内部的InvocationHandler，发起http post请求
        HelloService service = ProxyFactory.getProxy(HelloService.class);
        String result = service.sayHello("yukang");
        System.out.println(result);

        String resultStr = service.addStr("hello", "world");
        System.out.println(resultStr);

        Integer sumAB = service.addInteger(10, 90);
        System.out.println(sumAB);
    }
}
