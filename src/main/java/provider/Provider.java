package provider;

import framework.URL;
import protocol.http.HttpServer;
import provider.api.HelloService;
import provider.impl.HelloServiceImpl;
import register.Register;

/**
 * Created by Yuk on 2018/12/31.
 */
public class Provider {

    public static void main(String[] args) {
        // 注册服务：注册内容写入注册中心(本例注册中心是个txt文本)
        URL url = new URL("localhost", 8080);
        Register.regist(url, HelloService.class.getName(), HelloServiceImpl.class);

        // 启动tomcat,采用http方式，暴露服务
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }

}
