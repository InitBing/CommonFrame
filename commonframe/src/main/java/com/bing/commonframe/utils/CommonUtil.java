
package com.bing.commonframe.utils;

import android.content.Context;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class CommonUtil {
    private static final String TAG = CommonUtil.class.getSimpleName();

    public static void hideKeyBoard(Context ctx, View view) {
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyBoard(Context ctx, EditText view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

    public static boolean isNetworkConnected(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            return ni != null && ni.isConnectedOrConnecting();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 手机号验证 
     *
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    //限制中文
    public static String stringFilter(String str)throws PatternSyntaxException {
        String regEx  = "[^\\u4E00-\\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 校验银行卡卡号
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if(bit == 'N'){
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId){
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }


    //人民币转换
    public static String convertMoney2Yuan(int income) {
        StringBuilder stringBuilder = new StringBuilder();
        String unit = ".";
        int money = Math.abs(income);
        stringBuilder.delete(0, stringBuilder.length());
        if (money >= 100) {// 100转换后是１元。这个条件出现的频率比较大
            // do nothing
        } else if (money >= 10) {
            stringBuilder.append("0");
        } else {
            stringBuilder.append("00");
        }
        if (income<0)
            return "-"+stringBuilder.append(Integer.toString(money))
                .insert(stringBuilder.length() - 2, unit).toString();
        else
            return stringBuilder.append(Integer.toString(money))
                .insert(stringBuilder.length() - 2, unit).toString();// 目前长度至少是３
    }

    public static int getFontHeight(float fontSize){
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) + 2;
    }

    /**
     * 用户头像缩略图(120*120)
     * @param userIcon
     * @return
     */
    public static String getUserIconCompress(String userIcon){
        return userIcon + "v6";
    }

    /**
     * 动态单张（最长边500）
     * @param imageUrl
     * @return
     */
    public static String getV5ImageCompress(String imageUrl){
        return imageUrl + "v5";
    }

    /**
     * 动态多张（100*100）
     * @param imageUrl
     * @return
     */
    public static String getV1ImageCompress(String imageUrl){
        return imageUrl + "v1";
    }

    /**
     * Map转jsonStr
     * @param map
     * @return
     */
    public static String simpleMapToJsonStr(Map map){
        if(map==null||map.isEmpty()){
            return "null";
        }
        String jsonStr = "{";
        Set<?> keySet = map.keySet();
        for (Object key : keySet) {
            jsonStr += "\""+key+"\":\""+map.get(key)+"\",";
        }
        jsonStr = jsonStr.substring(0,jsonStr.length()-1);
        jsonStr += "}";
        return jsonStr;
    }


    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }


}
