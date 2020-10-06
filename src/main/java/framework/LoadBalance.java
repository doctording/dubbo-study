package framework;

import register.Register;

/**
 * @Author mubi
 * @Date 2020/10/6 23:11
 */
public class LoadBalance {

    public static URL getUrl(String interfaceName){
        URL url = Register.random(interfaceName);
        return url;
    }
}
