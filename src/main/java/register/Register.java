package register;

import framework.URL;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuk on 2018/12/31.
 */
public class Register {

    /**
     * Map<接口, Map<主机:端口, 接口实现类>>
     */
    private static Map<String, Map<URL, Class>> REGISTER = new HashMap<String, Map<URL, Class>>();

    /**
     * 注册服务（即:暴露接口）
     * @param url
     * @param interfaceName
     * @param implClass
     */
    public static void regist(URL url, String interfaceName, Class implClass) {
        Map<URL, Class> map = new HashMap(8);
        map.put(url, implClass);
        REGISTER.put(interfaceName, map);
        // 将REGISTER内容写入文本
        saveFile();
    }

    @Deprecated
    public static Class get(URL url,String interfaceName){
        return REGISTER.get(interfaceName).get(url);
    }

    /**
     * 模拟负载均衡，随机获取服务器
     * @param interfaceName
     * @return
     */
    public static URL random(String interfaceName) {
        Map<String, Map<URL, Class>> REGISTER_REMOTE = getRegisterFile();
        if (REGISTER_REMOTE != null) {
            return REGISTER_REMOTE.get(interfaceName).keySet().iterator().next();
        }
        return null;
    }

    private static String getFilePath(){
        // //设定为当前文件夹
        File directory = new File("");
        try {
            // 获取绝对路径
            String path = directory.getAbsolutePath();
            return path + "/" + "register.txt";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写入文本
     */
    public static void saveFile(){
        try {
            FileOutputStream fos = new FileOutputStream(getFilePath());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(REGISTER);
            oos.flush();
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 获取文本，读取 REGISTER 内容
     * @return
     */
    public static Map<String,Map<URL,Class>> getRegisterFile(){
        try {
            FileInputStream fis = new FileInputStream(getFilePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (Map<String,Map<URL,Class>>)ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从注册中心获取实现类（发现服务）
     * @param url
     * @param interfaceName
     * @return
     */
    public static Class getClass(URL url, String interfaceName) {
        Map<String, Map<URL, Class>> REGISTER_REMOTE = getRegisterFile();
        if (REGISTER_REMOTE != null) {
            return REGISTER_REMOTE.get(interfaceName).get(url);
        }
        return null;
    }

}
