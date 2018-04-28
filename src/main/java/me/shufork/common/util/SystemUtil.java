package me.shufork.common.util;

import java.io.File;

public abstract class SystemUtil {

    public static long nanoTime(){
        return System.nanoTime();
    }
    public static long microTime(){
        return nanoTime()/1000L;
    }
    public static long milliTime(){
        return nanoTime()/1000000L;
    }

    public static String removeLastPathSeparator(String path){
        if(path.endsWith(File.separator)){
            return path.substring(0,path.length() - File.separator.length());
        }
        return path;
    }
    public static String keepLastPathSeparator(String path){
        if(!path.endsWith(File.separator)){
            return path + File.separator;
        }
        return path;
    }
    /**
     * get os temp dir,no path separator at the end
     * @return
     */
    public static String osTempDir(){
        return removeLastPathSeparator(System.getProperty("java.io.tmpdir")) ;
    }

    public static void main(String[] args) {
        String s = osTempDir();
        System.out.println(s);
    }
}
