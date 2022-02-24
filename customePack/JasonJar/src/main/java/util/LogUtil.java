/**
 * 作者:冯家振
 * 创建时间:2022-2-21
 * 功能:自定义日志
 */


package util;

import android.util.Log;

public class LogUtil
{
   private static String className;//类名
   private static String methodName;//方法名
   private static int lineNumber;//行数
   private static boolean isDebuggable;
   private static String TAG="Jason";

    /**
     * 判断是否可以调试
     * @return
     */
    private void setIsDebuggable(boolean isDebuggable)
    {
        this.isDebuggable=isDebuggable;
    }

    //打印格式
    private static String createLog(String log ) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("====");
        buffer.append(methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")====:");
        buffer.append(log);
        return buffer.toString();
    }

    /**
     * 获取文件名、方法名、所在行数
     * @param sElements
     */
    private static void getMethodNames(StackTraceElement[] sElements){
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    public static void e(String message){
        if (isDebuggable)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.e(TAG+className, createLog(message));
    }

    public static void i(String message){
        if (isDebuggable)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.i(TAG+className, createLog(message));
    }

    public static void d(String message){
        if (isDebuggable)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.d(TAG+className, createLog(message));
    }

    public static void v(String message){
        if (isDebuggable)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.v(TAG+className, createLog(message));
    }

    public static void w(String message){
        if (isDebuggable)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.w(TAG+className, createLog(message));
    }
}
