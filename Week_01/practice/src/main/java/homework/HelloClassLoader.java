package homework;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program :        Week_01
 * @Author :         houke_zou
 * @date :           2021-01-09  20:06
 * @description :
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> hello = new HelloClassLoader().findClass("Hello");
        Object object = hello.newInstance();
        Method method = hello.getMethod("hello");
        method.invoke(object);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream resourceAsStream = HelloClassLoader.class.getClassLoader().getResourceAsStream("Hello.xlass");
        byte[] bytes = new byte[0];
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes1 = new byte[1024];
            int var5;
            while ((var5 = resourceAsStream.read(bytes1)) != -1) {
                byteArrayOutputStream.write(bytes1, 0, var5);
            }
            byteArrayOutputStream.close();
            bytes = byteArrayOutputStream.toByteArray();
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte)(255 - bytes[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.defineClass(name, bytes, 0, bytes.length);
    }
}
