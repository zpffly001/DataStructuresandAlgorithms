package xyz.zhoupf.algorithm_classification.greedyalgorithm;

import java.util.Arrays;

//换酒问题
public class _1518WineExchange {

    public static int numWaterBottles(int numBottles, int numExchange) {
        if (numBottles < numExchange) return numBottles;
        int sum = numBottles;
        int shengyu = numBottles;
        while (shengyu >= numExchange){
            int huanqu = shengyu / numExchange;
            sum += huanqu;
            shengyu = shengyu % numExchange + huanqu;
        }

        return sum;
    }

    public static int numWaterBottles02(int numBottles, int numExchange) {
        int bottle = numBottles, ans = numBottles;
        while (bottle >= numExchange) {
            bottle -= numExchange;
            ++ans;
            ++bottle;
        }
        return ans;
    }

    public static void merge(int[] A, int m, int[] B, int n) {
        int[] copyA = Arrays.copyOfRange(A, 0, m);
        System.out.println(Arrays.toString(copyA));
        int APoint = 0, BPoint = 0;
        for (int i = 0; i < A.length; i++) {

            if (APoint == (m - 1)){
                A[i] = B[BPoint++];
            }else if (BPoint == (n - 1)){
                A[i] = copyA[APoint++];
            }else if (copyA[APoint] <= B[BPoint]){
                A[i] = copyA[APoint++];
            }else {
                A[i] = B[BPoint++];
            }


        }
        System.out.println(Arrays.toString(A));
    }

    public static void main(String[] args) {
        //System.out.println(numWaterBottles(15, 4));
        //System.out.println(numWaterBottles02(15, 4));
        int[] a = {1,2,3,0,0,0};
        int[] b = {2,5,6};
        merge(a, 3, b, 3);
    }

}
