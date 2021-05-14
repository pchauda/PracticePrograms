import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Java8Features {

    public static void main(String[] args) {
        Java8Features obj = new Java8Features();

        List<String> list = new ArrayList<>();
        list.add(""); list.add("Prince"); list.add("");

        obj.removeEmptyStrings(list);
        System.out.println(list.size());

        // forEach method takes a Consumer. Any method that takes a argument and returns void can be replace here.
        list.forEach(System.out::println);
        // You can manually create a anonymous implementation of the Consumer and supply it as well
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        Java8Interface obj2 = obj.new Java8InterfaceImpl();
        obj2.getYear(); // Overridden method will be called.
        Java8Interface.getName();
        myStaticMethod(); // Static method can't be invoked using obj reference and need to be invoked directly or using Class.

        // Lambda based implementation for Runnable interface which is a function interface () -> void
        Runnable r = () -> System.out.println("Runnable implementation");
        r.run();

        // Compute methods in Map
        HashMap<String, String> map = new HashMap<>();
        map.compute("Riya", (k, v) -> v);
        map.computeIfAbsent("Prince", (k) -> k.toUpperCase());
        System.out.println(map);
    }

    public static void myStaticMethod() {}

    void removeEmptyStrings(List<String> list) {
        list.removeIf(String::isEmpty);
    }

    List<String> removeEmptyStringsUsingStream(List<String> list) {
        return list.stream().filter(String::isEmpty).collect(Collectors.toList());
    }

    interface Java8Interface {
        static void getName() {
            System.out.println("Java8Interface");
        }
        default void getYear() {
            System.out.println("2021");
        }
    }

    class Java8InterfaceImpl implements Java8Interface {
        public void getYear() {
            System.out.println("2021-Impl");
        }
    }

}
