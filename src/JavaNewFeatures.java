public class JavaNewFeatures {
    // Immutable classes using record keyword
    record Person(String name, int age) {
        public Person {
            if(name == null || age <= 0)
                throw new IllegalArgumentException("Name or Age can't be null");
        }
        // Non-canonical record constructor must delegate to another constructor
        public Person(String name, int age, boolean oldCons) {
            this(name, age);
        }
    }
    // Planet interface can only be implemented by Earth or Mars
    sealed interface Planet permits Earth, Mars {

    }
    // Earth interface can be implemented by any class
    non-sealed interface Earth extends Planet {}
    // interfaces can't be marked as final and hence sealed, there must a implementing class for sealed interface
    sealed interface Mars extends Planet {}
    // MarsMoon should be marked either sealed or non-sealed or final. In case of sealed a sub class must exist
    sealed class MarsMoon implements Mars{

    }
    non-sealed class MarsSecondMoon extends MarsMoon {

    }

    public static void main(String[] args) {
        // pattern matching for instanceof
        Object name = 10;
        if(name instanceof String s && s.length() > 0) {
            System.out.println("Name is a string");
        } else {
            System.out.println("Name is not a string");
        }
    }
}
