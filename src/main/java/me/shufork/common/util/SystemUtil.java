package me.shufork.common.util;

import java.io.File;

public abstract class SystemUtil {

    public static long NanoTime(){
        return System.nanoTime();
    }
    public static long MicroTime(){
        return NanoTime()/1000L;
    }
    public static long MilliTime(){
        return NanoTime()/1000000L;
    }

    public static String RemoveLastPathSeparator(String path){
        if(path.endsWith(File.separator)){
            return path.substring(0,path.length() - File.separator.length());
        }
        return path;
    }
    public static String KeepLastPathSeparator(String path){
        if(!path.endsWith(File.separator)){
            return path + File.separator;
        }
        return path;
    }
    /**
     * get os temp dir,no path separator at the end
     * @return
     */
    public static String OsTempDir(){
        return RemoveLastPathSeparator(System.getProperty("java.io.tmpdir")) ;
    }

    public static void main(String[] args) {
        String s = OsTempDir();
        System.out.println(s);
    }
}
