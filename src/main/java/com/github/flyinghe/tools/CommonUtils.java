package com.github.flyinghe.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * 该类用于封装一些常用方法。 该类依赖的外部jar包: commons-beanutils-1.9.2.jar ;
 * commons-logging-1.2.jar。
 *
 * @author Flying
 */
public class CommonUtils {
    private CommonUtils() {}

    /**
     * 返回一个随机UUID字符串，由数字和大写字母组成的32位字符串
     *
     * @return 返回一个随机UUID字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 该方法用于将一个存储了Bean类属性的Map对象中的key value 分别作为属性名和属性值， 赋值给该Bean类的一个实例。
     * 该方法只能将Map中的key与对应Bean中的属性名的属性赋值，通过setter器赋值，因此Bean必须符合JavaBean规范。
     *
     * @param property 一个Map对象，封装了相应的Bean类中的属性
     * @param clazz    Bean类的Class对象
     * @return 返回一个封装好的Bean类实例，封装失败返回null
     */
    public static <T> T toBean(Map<String, ? extends Object> property, Class<T> clazz) {
        T bean = null;
        try {
            bean = clazz.newInstance();
            BeanUtils.populate(bean, property);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bean;
    }

    /**
     * 将一个JavaBean对象的所有属性封装在Map中并返回，通过getter器获取该Bean属性
     *
     * @param bean 需要转换的Bean对象
     * @return 返回一个Map , 失败返回null
     */
    public static <T> Map<String, Object> toMap(T bean) {
        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
            map.remove("class");
            StringBuilder methodName = new StringBuilder("get");
            for (String property : map.keySet()) {
                methodName.delete(3, methodName.length()).append(property.substring(0, 1).toUpperCase())
                        .append(property.substring(1));
                Object o = MethodUtils.invokeMethod(bean, methodName.toString(), null);
                map.put(property, o);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    /**
     * 该方法用于将一个存储了Bean类属性的Map对象中的key value 分别作为属性名和属性值， 重新赋值给该Bean类的一个实例。
     * 该方法要求Bean类中的属性名与Map对象中的key值相对应, 此方法用于修改一个已存在对象的属性
     *
     * @param property 一个Map对象，封装了相应的Bean类中的属性
     * @param bean     需要修改的Bean类
     * @return 返回一个属性已修改的Bean类实例
     */
    public static <T> T modifyBean(Map<String, ? extends Object> property, T bean) {
        try {
            BeanUtils.populate(bean, property);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 复制一个对象并返回，属于浅度克隆，对象所属类必须符合JavaBean规范
     *
     * @param bean 被复制的对象
     * @return 返回一个被复制对象的一个副本
     */
    @SuppressWarnings("unchecked")
    public static <T> T cloneBean(T bean) {
        try {
            T newBean = (T) BeanUtils.cloneBean(bean);
            return newBean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭输入输出流
     *
     * @param in  输入流
     * @param out 输出流
     */
    public static void closeIOStream(InputStream in, OutputStream out) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据HTTP请求获取客户端IP地址
     *
     * @param request
     * @return IP
     */
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
