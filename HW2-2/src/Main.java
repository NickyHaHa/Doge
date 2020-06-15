import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        System.out.println("Q1 : ");
        while( input.hasNext() ){

            int n1 = input.nextInt();
            int n2 = input.nextInt();

            if(n1 == 0 && n2 == 0)System.out.println("想都別想，會壞掉");
            else
                System.out.println(
                        getGCDstream(n1, n2) + " " + getLCMstream(n1, n2)
                );
        }

    }

    //Recursive to get two numbers' gcd
    public static int GCD(int X, int Y){

        /*if(Y == 0)
            return X;
        else return GCD(Y, X % Y);*/
        return Y == 0 ? X : GCD(Y,X % Y);
    }

    //int...為"不定長度引數", reference: https://openhome.cc/Gossip/Java/Variable-lengthArgument.html
    //reduce(init-value, BinaryOperator<T>),在CH5 Working with streams.pdf的p.26
    public static int getGCDstream(int... num){

        return Arrays.stream(num)
                .reduce(0, (X, Y) -> GCD(X, Y));
    }

    //LCM = X * Y / GCD(X, Y)，兩數相乘除上最大公因數
    public static int getLCMstream(int... num){

        return Arrays.stream(num)
                .reduce(1, (X, Y) -> X * Y / GCD(X, Y));
    }
}
