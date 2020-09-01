package provider.api;

/**
 * Created by Yuk on 2018/12/31.
 */
public interface HelloService {

    String sayHello(String username);

    String addStr(String a, String b);

    Integer addInteger(Integer a, Integer b);
}
