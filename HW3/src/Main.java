import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static Deque<Integer> now = new ArrayDeque<>();
    static Deque<Integer> tmp = new ArrayDeque<>();

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        while( input.hasNext() ){

            int tri = input.nextInt();
            if(tri == 0)System.out.println("三角形沒有0啦!");

            else {

                //印空格排版
                IntStream.range(0, tri-2).forEach(i -> System.out.print(" "));
                System.out.println("N = " + tri);
                IntStream.range(0, tri).forEach(i -> System.out.print(" "));
                System.out.println(1);

                //兩個Deque分別存一個1進去
                now.addFirst(1);
                tmp.addFirst(1);

                //每做一次都會更新目前兩Deque中的元素
                IntStream.range(0, tri).forEach(i -> Pascal(i, tri, tmp, now));

                now.clear();
                tmp.clear();
            }
        }
    }

    public static void Pascal(int space, int N, Deque<Integer> A, Deque<Integer> B){

        List<Integer> Pre = new ArrayList<>();
        List<Integer> Aft = new ArrayList<>();

        //第一個Deque的頭插入0，第二個Deque的尾插入0
        A.addFirst(0);
        B.addLast(0);

        //將兩個Deque轉成List再去做相加
        A.stream().forEach(i -> Pre.add(i));
        B.stream().forEach(i -> Aft.add(i));

        IntStream.range(0, Pre.size())
                .forEach(i -> Aft.set(i, Pre.get(i) + Aft.get(i)));

        now.clear();
        tmp.clear();

        //相加完成的List再更新原兩個Deque
        Aft.stream().forEach(i -> now.addLast(i));
        Aft.stream().forEach(i -> tmp.addLast(i));

        //印空格排版+輸出Deque
        IntStream.range(0, N-space-1).forEach(i -> System.out.print(" "));
        now.stream().forEach(i -> System.out.print(i + " "));
        System.out.println("");
    }
}
