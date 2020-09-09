package com.cloudpick.yunnasdk.tools.rackview;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.lang.reflect.Method;


/**
 * Created by maxwell on 17-12-13.
 */

public class Tools {

    private static final String TAG = "CloudPick";

    public static boolean isAppInstalled(Context context, String packageName) {
        if (null == context || null == packageName) {
            return false;
        }
        boolean bHas = true;
        try {
            //packageManager.getInstalledPackages  这种方式有的手机需要权限;  packageManager.getPackageInfo不需要权限
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_GIDS);
        } catch (PackageManager.NameNotFoundException e) {
            // 抛出找不到的异常，说明该程序已经被卸载
            bHas = false;
        }
        return bHas;
    }


    /**
     * 判断是否存在虚拟按键
     *
     * @param context context
     * @return true or false
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }

    /**
     * 使用*隐藏手机号部分数字
     *
     * @param phone 手机号
     * @return
     */
    public static String hidePartialPhone(String phone) {
        try {
            String ret = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            return ret;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return phone;
        }

    }

    /**
     * 显示Toast消息
     *
     * @param context
     * @param msg
     */
    public static void ToastMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示Toast消息
     *
     * @param context
     * @param resId
     */
    public static void ToastMessage(Context context, int resId) {
        String msg = context.getResources().getString(resId);
        ToastMessage(context, msg);
    }


    /**
     * 隐藏软键盘
     *
     * @param context
     * @param token
     */
    public static void hideSoftInput(Context context, IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static int dp2px(Context context, float dpValue) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * m + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / m + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static void Sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ex) {

        }
    }

    public static String getEnv() {
//       todo
        return "";
//        String env = AppData.getAppData().getAsString(Constants.KEY_ENV_HOST);
//        return env;
    }

    /**
     * todo
     * 阿里服务器
     */
    public static boolean envIsAli() {
        return true;
//        String env = getEnv();
//        String[] arr = BuildConfig.DEBUG ? Constants.serversTest : Constants.servers;
//        if (BuildConfig.DEBUG){
//            return  arr[0].equalsIgnoreCase(env)||arr[1].equalsIgnoreCase(env);
//        }else{
//            return arr[0].equalsIgnoreCase(env);
//        }
    }

    // 判断一个字符是否是中文
    public static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
    }

    // 判断一个字符是否是英文
    public static boolean isEn(char c) {
        if(c>='a'&&c<='z'||c>='A'&&c<='Z'){
            // TODO
            return true;
        }else{
            return false;
        }
    }

    // 判断一个字符串是否含有中文
    public static boolean isChinese(String str) {
        if (str == null)
            return false;
        for (char c : str.toCharArray()) {
            if (isChinese(c)||isEn(c))
                return true;// 有一个中文字符就返回
        }
        return false;
    }
    public static void copyText(Context context, String text, String toastStr) {
        //1. 复制字符串到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setPrimaryClip(ClipData.newPlainText(null, text));
        if (!TextUtils.isEmpty(toastStr)) {
            Toast.makeText(context, toastStr, Toast.LENGTH_SHORT).show();
        }
    }

}
