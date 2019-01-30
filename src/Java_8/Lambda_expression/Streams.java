package Java_8.Lambda_expression;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(System.out::println);
        System.out.println("=========");
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
        System.out.println("=========");

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        //get list of unique squares
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);
        System.out.println("=========");
        Random random2 = new Random();
        random2.ints().limit(10).sorted().forEach(System.out::println);

        System.out.println("=========");
        List<String> stringsDemo = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

        //get count of empty string
        long count = stringsDemo.parallelStream().filter(string -> string.isEmpty()).count();
        stringsDemo.forEach(System.out::println);
        System.out.println("=========");
        List<String>strings2 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered2 = strings2.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("Filtered List: " + filtered2);
        String mergedString = strings2.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Merged String: " + mergedString);

        //https://www.tutorialspoint.com/java8/java8_streams.htm
    }
}
