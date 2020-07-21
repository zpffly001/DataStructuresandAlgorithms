package chuji_shuzu;

import java.util.Arrays;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 */

public class chuji_shuzu07 {

    public static int[] plusOne(int[] digits) {

//        int length = digits.length;
//        // 如果最后一位不是9的情况的处理方式
//        if(digits[length - 1] != 9){
//            digits[length - 1] = digits[length - 1] + 1;
//            return digits;
//        }
//        boolean addFlag = true;
//        // 如果数组的所有位都是9的情况 进行处理
//        for(int j = 0; j<length;j++){
//            if(digits[j] != 9){
//                addFlag = false;
//            }
//        }
//        if(addFlag){
//            int[] result = new int[length+1];
//            for(int k=0;k<length+1;k++){
//                if(k==0){
//                    result[k] = 1;
//                }else{
//                    result[k] = 0;
//                }
//            }
//            return result;
//        }
//
//        // 如果最后一位是9的情况
//        int i = length-1;
//        // 声明一个新的数组
//        int[] array = new int[length];
//        while(digits[i] == 9){
//            // 如果是最后一位 只是进位
//            array[i] = 0;
//            array[i-1] = digits[i-1]+1;
//            i--;
//        }
//        // 判断如果补位没补完，把剩下的位数补充完整
//        if(i == 0 && length>1 && digits[1] != 9){
//            array[i] = digits[i];
//        }
//        if(i>0){
//            for(int m = 0;m<i; m++){
//                array[m] = digits[m];
//            }
//        }
//        return array;



//        int length = digits.length;
//        //如果最后一位不是9，且数组不是只有0这一个元素的情况下
//        if (digits[length-1] != 9 && digits[0] != 0){
//            digits[length-1] = digits[length-1] + 1;
//            return  digits;
//        }
//
//        //如果数组所有位都是9的情况下
//        boolean flag = true;
//        //如果数组全是9，则flag=true,否则flag=false
//        for (int j = 0; j < length; j++){
//            if (digits[j] != 9){
//                flag = false;
//            }
//        }
//        if (flag){
//            int[] result = new int[length + 1];
//            for (int k = 0; k < length+1; k++){
//                if (k==0){
//                    result[k] = 1;
//                }else {
//                    result[k] = 0;
//                }
//            }
//            return result;
//        }
//
//        //如果最后一位是9，且数组不全是9的情况下
//        int i = length -1;
//        int[] array = new int[length];
//        if (digits[0] == 0){
//            int[] result = new int[2];//这里明明只有两个值，但是你如果把数组定义成result[1]则就会发生数组越界
//            result[0] = 1;
//            result[1] = 0;
//            return result;
//        }
//        //做一个循环，是9则进位补零，直到当前位的值不是9
//        while (digits[i] == 9){
//            array[i] = 0;
//            array[i-1] = digits[i-1] + 1;
//            i--;
//        }
//        //因为不是所有位都是9，则有digits数组中的数没全部补充到array数组中，所以要对剩下的几位复制到array数组相应的位置
//        //如果只剩下第一位即digits[0]不为9，则上面的循环就够了
//        //但是循环中有i--,所以我们还需要考虑一种情况即digits[0]和digits[1]都不为9,则循环把digits[1]复制到了array数组，digits[0]还没有复制到array数组
//        if (i == 0 && length > 1 && digits[1] != 9){
//            array[i] = digits[0];
//        }
//        //如果剩下的好几位都不为9,这个和上面这两个if判断语句的i值都是经过while循环--后的值
//        if (i > 0){
//            for (int m = 0; m < i; m++){
//                array[m] = digits[m];
//            }
//        }
//        return array;


        int len=digits.length;
        for(int i=len-1;i>=0;i--){
            if(digits[i]==9){
                digits[i]=0;
            }else{
                digits[i]+=1;
                return digits;
            }
        }
        System.out.println(digits[3]);
        int[] new_digits=new int[digits.length+1];
        new_digits[0]=1;
        return new_digits;

    }

    public static void main(String[] args) {
        int [] nums = {1,9,9,9};
        int [] arr = plusOne(nums);
        System.out.println(Arrays.toString(arr));
    }

}
