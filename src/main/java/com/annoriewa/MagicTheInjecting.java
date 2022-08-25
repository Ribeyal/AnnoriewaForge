package com.annoriewa;

import java.lang.reflect.Method;
import java.security.ProtectionDomain;
 
public class MagicTheInjecting extends Thread {
 
    public static byte[][] classes;
 
    @Override
    public void run() {
        try {
            ClassLoader loader = getLoader();
 
            Class<?> MCHackedLoader = null;
            for (byte[] classData : classes) {
                Class<?> cls = defineClass(classData, loader);
                if(cls.getName().equals("com.annoriewa.Annoriewa"))
                {
                    MCHackedLoader = cls;
                }
            }
 
            MCHackedLoader.newInstance();
        } catch(Exception ex) { }
    }
 
    private static ClassLoader getLoader() {
        try {
            for(Thread t : Thread.getAllStackTraces().keySet()) {
                ClassLoader loader = t.getContextClassLoader();
                if(loader != null && (
                        loader.toString().contains("net.minecraft.launchwrapper.Launch")
                                || loader.toString().contains("net.minecraft.launchwrapper.LaunchClassLoader"))
                ) {
                    return loader;
                }
            }
        } catch(Exception ex) {}
        return null;
    }
 
    private Class<?> defineClass(byte[] classData, ClassLoader loader) {
        try {
            Method dc = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class, ProtectionDomain.class);
            dc.setAccessible(true);
            return (Class<?>)dc.invoke(loader, null, classData, 0, classData.length, null);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
 
    public static int injectCP(byte[][] classes) {
        try {
            MagicTheInjecting.classes = classes;
            new MagicTheInjecting().start();
        } catch (Exception ex) { }
        return 0;
    }
 
    public static byte[][] getByteArray(int size) {
        return new byte[size][];
    }
}