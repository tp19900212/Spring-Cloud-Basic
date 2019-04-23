package quyc.learn.lambda.functional._10bibao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author: andy
 * @create: 2019/4/23 15:24
 * @description: 使用闭包捕获状态
 * lambda 表达式是无状态的，而闭包是带有状态的。将 lambda 表达式替换为闭包，是一种管理函数式程序中的状态的好方法。
 *
 * <a src=https://www.ibm.com/developerworks/cn/java/j-java8idioms10/index.html?ca=drs-></a>
 */
public class BiBao {

    public static void main(String[] args) {
        simpleLambda();
        print();
        printLambda();
        biBaoLambda();
    }

    /**
     * 简单的lambda表达式
     * <p>
     * 这两个 lambda 表达式都依赖于传入的参数和字面常量。二者都是独立的，这意味着它们没有任何外部依赖项。
     * 因为它们依赖于传入的参数，而且可能还依赖于一些常量，所以 lambda 表达式是无状态的。
     * <p>
     * 如果我们希望计算给定值的三倍或四倍，该怎么办？
     */
    private static void simpleLambda() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> collect = numbers.stream().filter(x -> x % 2 == 0).map(x -> x * 2).collect(toList());
        // 方法 map 要求接受函数接口 Function<T, R> 的一个实现作为参数。
        // 如果我们传入该接口外的任何内容（比如一个 BiFunction<T, U, R>），map 不会接受。
//         int factor = 3;
//         List<Integer> collect = numbers.stream().filter((x,factor) -> x % factor == 0).map(x -> x * 2).collect(Collectors.toList());
        System.out.println("numbers = " + numbers);
        System.out.println("collect = " + collect);
    }

    /**
     * 匿名内部类接受外部参数
     */
    private static void print() {
        String location = "World";
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello " + location);
            }
        };
        runnable.run();
    }

    /**
     * lambda 表达式中的变量 location 被绑定到 lambda 表达式的词法范围中的变量 location。严格来讲，此代码中的 lambda 表达式是一个闭包。
     */
    private static void printLambda() {
        String location = "World";
        Runnable runnable = () -> System.out.println("Hello " + location);
        runnable.run();
    }

    private static void biBaoLambda() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        int factor = 3;
        List<Integer> collect = numbers.stream().filter(x -> x % 2 == 0).map(x -> x * factor).collect(toList());
        System.out.println("collect = " + collect);
    }

}

class Sample {
    public static void call(Runnable runnable) {
        System.out.println("calling runnable");

        //level 2 of stack
        runnable.run();
    }

    public static void main(String[] args) {
        //level 1 of stack
        int value = 4;
        call(
                //level 3 of stack
                () -> System.out.println(value)
        );
    }
}

class Sample2 {
    public static Runnable create() {
        int value = 4;
        Runnable runnable = () -> System.out.println(value);

        System.out.println("exiting create");
        return runnable;
    }

    public static void main(String[] args) {
        Runnable runnable = create();

        System.out.println("In main");
        runnable.run();
    }
}