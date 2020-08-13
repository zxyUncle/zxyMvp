package com.cloudpick.yunnasdk.tools;

import android.util.Log;

import com.cloudpick.yunnasdk.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * json格式化工具
 *
 * @author Young
 */
public class LogcatUitls {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.i(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.i(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    public static void printStirng(String tag, String msg) {
        printLine(tag, true);
        Log.i(tag, "║ " + msg);
        printLine(tag, false);
    }

    public static void printGet(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            printLine(tag, true);
            Log.i(tag, "║ " + msg);
            printLine(tag, false);
        }
    }

    public static void printPost(String tag, String header, String msg) {
        if (BuildConfig.DEBUG) {
            printLine(tag, true);
            Log.i(tag, "║ " + header);
            String message;
            try {
                if (msg.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(msg);
                    message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
                } else if (msg.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(msg);
                    message = jsonArray.toString(4);
                } else {
                    message = msg;
                }
            } catch (JSONException e) {
                message = msg;
            }

            message = LINE_SEPARATOR + message;
            String[] lines = message.split(LINE_SEPARATOR);
            for (String line : lines) {
                Log.i(tag, "║ " + line);
            }
            printLine(tag, false);
        }
    }

    public static void printJson(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            String message;
            try {
                if (msg.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(msg);
                    message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
                } else if (msg.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(msg);
                    message = jsonArray.toString(4);
                } else {
                    message = msg;
                }
            } catch (JSONException e) {
                message = msg;
            }

            printLine(tag, true);
            message = LINE_SEPARATOR + message;
            String[] lines = message.split(LINE_SEPARATOR);
            for (String line : lines) {
                Log.i(tag, "║ " + line);
            }
            printLine(tag, false);
        }
    }
}

