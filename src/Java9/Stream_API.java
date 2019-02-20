package Java9;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream_API {
    public static void main(String[] args) {
        Stream.of("a","b","c","","e","f").takeWhile(s->!s.isEmpty())
                .forEach(System.out::print);
        System.out.println("======");
        Stream.of("a","b","c","","e","f").dropWhile(s-> !s.isEmpty())
                .forEach(System.out::print);

        System.out.println();
        Stream.of("a","b","c","","e","","f").dropWhile(s-> !s.isEmpty())
                .forEach(System.out::print);
        System.out.println();
        IntStream.iterate(3, x -> x < 10, x -> x+ 3).forEach(System.out::println);
        System.out.println("NUll able");

        long count = Stream.ofNullable(100).count();
        System.out.println(count);

        count = Stream.ofNullable(null).count();
        System.out.println(count);
    }
}
