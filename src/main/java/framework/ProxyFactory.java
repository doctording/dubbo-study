package framework;

import protocol.http.HttpClient;
import register.Register;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Yuk on 2018/12/31.
 */
public class ProxyFactory<T> {

    public static <T> T getProxy(final Class interfaceClass){
        return (T)Proxy.newProxyInstance(
                interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 方法调用请求体构造
                Invocation invocation = new Invocation(
                        interfaceClass.getName(),
                        method.getName(),
                        args,
                        method.getParameterTypes()
                );
                // 即注册中心负载均衡获取接口所对应的服务器
                URL url = LoadBalance.getUrl(interfaceClass.getName());

                // 模拟远程调用（网络调用）并返回
                HttpClient httpClient = new HttpClient();
                String rs = httpClient.post(url.getHostname(),url.getPort(),invocation);

                Class returnType = method.getReturnType();
                // 这里返回理论上是要反序列化成returnType
                // TODO
                if(returnType.getSimpleName().equals(Integer.class.getSimpleName())){
                    return Integer.valueOf(rs);
                }
                return rs;
            }
        });
    }
}
