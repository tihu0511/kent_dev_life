package org.jigang.classloader;

/**
 * Created by wujigang on 16/7/8.
 */
public class NetClassLoaderSimple {
    private NetClassLoaderSimple instance;

    public void setInstance(Object obj) {
        this.instance = (NetClassLoaderSimple) obj;
    }

    public static void main(String[] args) throws ClassNotFoundException {

//        ClassLoader classLoader = NetClassLoaderSimple.class.getClassLoader();

        ClassLoader classLoader = new NetworkClassLoader("http://localhost:8086/classes");

        String className = "org.jigang.classloader.NetClassLoaderSimple";

        Class cla = classLoader.loadClass(className);
        System.out.println(cla.getCanonicalName());

        System.out.println();
        while (classLoader != null) {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
    }
}
