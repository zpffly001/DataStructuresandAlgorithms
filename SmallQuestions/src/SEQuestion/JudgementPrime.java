package SEQuestion;

import java.util.Scanner;

/**
 * Java中判断素数的五种方法
 */
public class JudgementPrime {

    public static void main(String[] args) {
        test01();
        test02();
        test03();
    }

    //1. 从 2 到 x-1 测试是否可以整除
    public static void test01(){
        Scanner in = new Scanner(System.in);

        int x = in.nextInt();
        boolean isPrime = true;
        if ( x == 1)
        {
            isPrime = false;
        }
        for( int i = 2; i< x; i++)
        {
            if(x % i ==0)
            {
                isPrime = false;
                break;
            }
        }
        if( isPrime)
        {
            System.out.println(x +"是素数");

        }
        else
        {
            System.out.println(x+ "不是素数");
        }
    }

    //改进版，时间复杂度为 O(n/2)
    //2. 去掉偶数后，从 3 到 x-1, 每次加 2
    public static void test02(){
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        boolean isPrime = true;

        if(x ==1 || x %2 ==0 && x !=2 )
        {
            isPrime = false;
        }
        else
        {
            for(int i =2; i<x; i +=2)
            {
                if( x % i == 0)
                {
                    isPrime = false;
                    break;
                }
            }
        }
        if( isPrime)
        {
            System.out.println(x +"是素数");

        }
        else
        {
            System.out.println(x+ "不是素数");
        }
    }

    //3. 2 方法上的改进版，只需到 sqrt(x) 即可以
    //数学上可以证明，sqrt(x) 即 x 的平方根，时间复杂度为 O(sqrt(n))
    public static void test03(){
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        boolean isPrime = true;

        if(x ==1 || x %2 ==0 && x !=2 )
        {
            isPrime = false;
        }
        else
        {
            for( int i =3; i< Math.sqrt(x); i+=2)
            {
                if( x % i == 0)
                {
                    isPrime = false;
                    break;
                }
            }
        }
        if( isPrime)
        {
            System.out.println(x +"是素数");
        }
        else
        {
            System.out.println(x+ "不是素数");
        }
    }

    //4. 找出前 50 个素数
    //判断是否能被已知的的且 <x 的素数整除
    //这个方法可扩展性很强，建议掌握。
    public static void test04(){
        int [] primes = new int[50];
        primes[0] =2;
        int cnt =1;
        Main:
        for(int x= 3; cnt<primes.length; x++)
        {
            for(int i = 0; i< cnt; i++)
            {
                if( x % primes[i] == 0)
                {
                    continue Main;
                }
            }
            primes[cnt++] = x;

        }
        for ( int k: primes)
        {
            System.out.print(k+ " ");
        }
    }

    //5. 用计算机的语言去思考
    //构造素数表，构造 n 以内的素数表
    /**
    原理：
    令 x =2;
    将 2x、3x、4x 直至 ax<n 的数标记为非素数
    令 x 为下一个没有被标记为非素数的数，重复 2；直至所有的数都已尝试完毕。
     */
    public static void test05(){
        boolean[] isPrime = new boolean[100];
        for( int i =2; i< isPrime.length; i++)
        {
            isPrime[i] = true;
        }
        for(int i =2; i<isPrime.length; i++)
        {
            if( isPrime [i])
            {
                for(int k=2; i*k < isPrime.length; k++)
                {
                    isPrime[i*k] = false;
                }
            }
        }
        for( int i = 0; i<isPrime.length; i++)
        {
            if(isPrime[i])
            {
                System.out.print(i+ " ");
            }
        }
    }

}
