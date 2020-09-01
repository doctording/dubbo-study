**手写dubbo框架**

博客地址：https://blog.csdn.net/yu_kang/article/details/85413575

---

dubbo官网:<a href="http://dubbo.apache.org/zh-cn/docs/user/preface/architecture.html">dubbo arch</a>

![](http://dubbo.apache.org/docs/zh-cn/user/sources/images/dubbo-architecture.jpg)

* consumer从运行容器获得到代理对象，执行service 方法调用时，使用反射
* 底层有网络协议和序列化协议，本例使用了http协议，没有使用序列化
* 本例注册中心使用了本地文本文件，主要是存储接口与其对应到实现到映射关系
* Provider暴露服务，注册到注册中心
