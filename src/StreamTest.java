import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class StreamTest {

    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>(); list.add(1); list.add(2); list.add(1); list.add(2); list.add(3);

        Map<Integer, Long> listMap = list.stream().collect(groupingBy(Function.identity(), counting()));
        listMap.entrySet().forEach(t -> System.out.println("key=" + t.getKey() + ", value="+ t.getValue()));

        Stream.of("Prince", "Chauda", " Riya", "Malik").map(t -> {
            System.out.println("name: " + t); return t.toUpperCase();}).forEach(System.out::println);

        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));
        System.out.println(streamSupplier.get().anyMatch(s -> true));   // ok
        System.out.println(streamSupplier.get().noneMatch(s -> true));   // ok

    }

}