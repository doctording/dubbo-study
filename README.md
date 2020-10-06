**手写dubbo框架**

博客地址：https://blog.csdn.net/yu_kang/article/details/85413575

---

dubbo官网:<a href="http://dubbo.apache.org/zh-cn/docs/user/preface/architecture.html">dubbo arch</a>

![](http://dubbo.apache.org/docs/zh-cn/user/sources/images/dubbo-architecture.jpg)

* consumer从运行容器获得到接口代理对象，执行接口方法调用，就能获取到结果
* 其中框架：先根据接口全限定名：1.去获取到接口的注册的主机名称；2.构造请求体；3.发送http请求

* Provider暴露服务，注册到注册中心:包括：1.本地注册; 2.远程注册
* 1. http请求接收后做解析；2. 再从注册中心获取到接口到实现类；3. 那么就能反射执行并通过网络返回
