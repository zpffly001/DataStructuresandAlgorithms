package xyz.zhoupf.mianshi;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;

public class Solution {
    /**
     * 获取密码安全等级
     * @param
     * @return string字符串
     */

    public static void main(String[] args) {
        //System.out.println(getSecurityLevel());
        ArrayList<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.remove(0);
        System.out.println(list);
        LinkedList<Object> linkedList = new LinkedList<>();
        //linkedList.ad
    }

    public static String getSecurityLevel (String password) {
        // write code here
        int score = 0;
        int length = password.length();
        if (length<=4){
            score += 5;
        }else if (length <= 7){
            score += 10;
        }else {
            score += 25;
        }

        int big = 0, small = 0, num = 0, fuhao = 0;

        char[] array = password.toCharArray();
        for (char c : array) {
            if (97 <= c && c <= 122){
                small += 1;
            }else if (65 <= c && c <= 90){
                big += 1;
            }else if (48 <= c && c <= 57){
                num += 1;
            }else {
                fuhao += 1;
            }
        }

        if (big + small == 0){
            score += 0;
        }else if (big == length || small == length){
            score += 10;
        }else if ( 0 < (big + small) && (big + small) < length){
            score += 20;
        }

        if (num == 0) {
            score += 0;
        } else if (num == 1) {
            score += 10;
        }else if (num > 1){
            score += 20;
        }

        if (fuhao == 0) {
            score += 0;
        }else if (fuhao == 1){
            score += 10;
        }else if (fuhao > 1){
            score += 20;
        }

        if (big > 0 && small > 0 && num > 0 && fuhao > 0) {
            score += 5;
        }else if ((big > 0 && small > 0) && num > 0 && fuhao > 0){
            score += 3;
        }else if (num > 0 && fuhao > 0){
            score += 2;
        }

        String dengji = "";

        if (score >= 90) {
            dengji = "VERY_SECURE";
        } else if (score >= 80) {
            dengji = "SECURE";
        }else if (score >= 70) {
            dengji = "VERY_STRONG";
        }else if (score >= 60) {
            dengji = "STRONG";
        }else if (score >= 50) {
            dengji = "AVERAGE";
        }else if (score >= 25) {
            dengji = "WEAK";
        }else {
            dengji = "VERY_WEAK";
        }

        return dengji;
    }

    public int getDoublePrimes (int index) {
        // write code here
        int num = 0;
        int k = 2;
        if (index > 200) {
            return -1;
        }else {
            while (num < index){
                boolean flag1 = true, flag2 = true;
                for (int i = 2; i <= k; i++) {
                    if (k % i == 0 && k != i){
                        flag1 = false;
                        break;
                    }
                }
                String strK = String.valueOf(k);
                char[] chars = strK.toCharArray();
                String newK = "";
                for (int i = chars.length - 1; i >=0; i--) {
                    newK += chars[i];
                }
                int newNumK = Integer.parseInt(newK);
                for (int i = 2; i <= newNumK; i++) {
                    if (newNumK % i == 0 && newNumK != i){
                        flag2 = false;
                        break;
                    }
                }

                if (flag1 && flag2) {
                    num++;
                }
                k++;
            }
            return --k;
        }
    }

}