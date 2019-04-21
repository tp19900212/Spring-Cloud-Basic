package quyc.learn.lambda.streams;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by quyuanchao on 2019/4/21 16:49.
 * <p>Title: Streams Lambda</p>
 * <p>Description: Streams Learn</p>
 */
public class Streams {
    private static List<Person> javaProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
            add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
            add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
            add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
            add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
            add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
            add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
            add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
            add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
        }
    };

    private static List<Person> phpProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
            add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
            add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
            add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
            add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
            add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
            add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
            add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
            add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
            add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
        }
    };

    public static void main(String[] args) {
//        simpleForeach();
//        learnFilter();
//        learnLimit();
//        streamSort();
//        minOrMax();
//        collectMap();
//        parallel();
        summaryStatistics();
    }

    /**
     * printf 格式
     * %c        单个字符
     * %d        十进制整数
     * %f        十进制浮点数
     * %o        八进制数
     * %s        字符串
     * %u        无符号十进制数
     * %x        十六进制数
     * %%        输出百分号%
     */
    private static void simpleForeach() {
        System.out.println("所有程序员的姓名：");
        javaProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        System.out.println("");
        phpProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        System.out.println("");
        System.out.println("给程序员加薪 5% :");
        javaProgrammers.forEach(new Consumer<Person>() {
            @Override
            public void accept(Person p) {
                p.setSalary(p.getSalary() / 100 * 5 + p.getSalary());
            }
        });
        phpProgrammers.forEach((p) -> p.setSalary(p.getSalary() / 100 * 5 + p.getSalary()));
        javaProgrammers.forEach((p) -> System.out.printf("%s %s %d; ", p.getFirstName(), p.getLastName(), p.getSalary()));
        System.out.println("");
        phpProgrammers.forEach((p) -> System.out.printf("%s %s %d; ", p.getFirstName(), p.getLastName(), p.getSalary()));
    }

    /**
     * 显示月薪超过1400美元的PHP程序员
     */
    private static void learnFilter() {
        System.out.println("下面是月薪超过 $1,400 的PHP程序员:");
        phpProgrammers.stream().filter((p) -> p.getSalary() > 1400)
                .forEach((p) -> System.out.printf("%s %s %d; ", p.getFirstName(), p.getLastName(), p.getSalary()));
        System.out.println("");
        System.out.println("下面是年龄大于 24岁且月薪在$1,400以上的女PHP程序员:");
        Predicate<Person> ageFilter = (p) -> p.getAge() > 24;
        Predicate<Person> salaryFilter = (p) -> p.getSalary() > 1400;
        Predicate<Person> genderFilter = (p) -> "female".equals(p.getGender());
        phpProgrammers.stream().filter(ageFilter).filter(salaryFilter).filter(genderFilter)
                .forEach((p) -> System.out.printf("%s %s %d; ", p.getFirstName(), p.getLastName(), p.getSalary()));

    }

    /**
     * 使用limit方法，可以限制结果集的个数
     */
    private static void learnLimit() {
        Predicate<Person> genderFilter = (p) -> "female".equals(p.getGender());
        System.out.println("最前面的3个 Java programmers:");
        javaProgrammers.stream().limit(3).forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        System.out.println("");
        System.out.println("最前面的3个女性 Java programmers:");
        // 链式处理的先后顺序决定了最后的结果，要注意先后顺序
        javaProgrammers.stream().filter(genderFilter).limit(3).forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
    }

    /**
     * 使用 stream 进行排序
     */
    private static void streamSort() {
        System.out.println("根据 name 排序,并显示前5个 Java programmers:");
        List<Person> sortByName = javaProgrammers
                .stream()
                .sorted((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()))
                .limit(5)
                .collect(Collectors.toList());
        sortByName.forEach((p) -> System.out.printf("%s %s; %n", p.getFirstName(), p.getLastName()));
        System.out.println("根据 salary 排序 Java programmers:");
        List<Person> sortBySalary = javaProgrammers
                .stream()
                .sorted((p1, p2) -> p1.getSalary() - p2.getSalary())
                .collect(Collectors.toList());
        sortBySalary.forEach((p) -> System.out.printf("%s %s %d; ", p.getFirstName(), p.getLastName(), p.getSalary()));
    }

    /**
     * 使用 stream 取最大或最小值
     */
    private static void minOrMax() {
        System.out.println("工资最低的 Java programmer:");
        Person minSalary = javaProgrammers.stream().min((p1, p2) -> p1.getSalary() - p2.getSalary()).get();
        System.out.printf("%s %s %d", minSalary.getFirstName(), minSalary.getLastName(), minSalary.getSalary());
        System.out.println("");
        System.out.println("工资最高的 Java programmer:");
        Person maxSalary = javaProgrammers.stream().max((p1, p2) -> p1.getSalary() - p2.getSalary()).get();
        System.out.printf("%s %s %d", maxSalary.getFirstName(), maxSalary.getLastName(), maxSalary.getSalary());
    }

    /**
     * 使用 collect 和 map 对集合数据进行抽取
     */
    private static void collectMap() {
        System.out.println("将 PHP programmers 的 first name 拼接成字符串:");
        String phpFirstNames = phpProgrammers.stream().map(Person::getFirstName).collect(Collectors.joining("; "));
        System.out.println("phpFirstNames = " + phpFirstNames);
        System.out.println("将 Java programmers 的 first name 存放到 Set:");
        Set<String> javaFirstNameSet = javaProgrammers.stream().map(Person::getFirstName).collect(Collectors.toSet());
        javaFirstNameSet.forEach(System.out::println);
        System.out.println("将 Java programmers 的 first name 存放到 TreeSet:");
        Collection<String> javaFirstNameTreeSet = javaProgrammers.stream().map(Person::getFirstName).collect(Collectors.toCollection(TreeSet::new));
        javaFirstNameTreeSet.forEach(System.out::println);
    }

    /**
     * parallel
     */
    private static void parallel() {
        System.out.println("计算付给 Java programmers 的所有money:");
        int sumSalary = javaProgrammers.parallelStream().mapToInt(Person::getSalary).sum();
        System.out.println("sumSalary = " + sumSalary);
        int sum = javaProgrammers.stream().mapToInt(Person::getSalary).sum();
        System.out.println("sum = " + sum);
    }

    /**
     * 使用 SummaryStatistics 获取集合的一些统计数据
     */
    private static void summaryStatistics() {
        //计算 count, min, max, sum, and average for numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntSummaryStatistics intSummaryStatistics = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("intSummaryStatistics.getAverage() = " + intSummaryStatistics.getAverage());
        System.out.println("intSummaryStatistics.getCount() = " + intSummaryStatistics.getCount());
        System.out.println("intSummaryStatistics.getMax() = " + intSummaryStatistics.getMax());
        System.out.println("intSummaryStatistics.getMin() = " + intSummaryStatistics.getMin());
        System.out.println("intSummaryStatistics.getSum() = " + intSummaryStatistics.getSum());
    }
}
