package protocol.http;

import framework.Invocation;
import framework.URL;
import org.apache.commons.io.IOUtils;
import register.Register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

/**
 * Created by Yuk on 2018/12/31.
 */
@SuppressWarnings("unchecked")
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp){

        try{
            // Http请求流转为请求对象
            InputStream is = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            // 请求对象 Invocation，包括了接口类，方法名，方法参数类型，方法参数具体值
            Invocation invocation = (Invocation)ois.readObject();

            // 反射执行调用并返回
            Class implClass = Register.getClass(new URL("localhost",8080),invocation.getInterfaceName());
            Method method = implClass.getMethod(invocation.getMethodName(),invocation.getParamTypes());
            // 执行结果，这里执行结果要执行序列化
            // TODO
            Object resultObj = method.invoke(implClass.newInstance(),invocation.getParams());
            String result;
            if(resultObj instanceof String) {
                result = (String)resultObj;
            }else {
                result = String.valueOf(resultObj);
            }
            System.out.println(result);

            // 将结果返回
            IOUtils.write(result,resp.getOutputStream());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
