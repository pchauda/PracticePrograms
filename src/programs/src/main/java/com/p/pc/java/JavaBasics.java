package com.p.pc.java;

public class JavaBasics {
    private JavaBasics() {
        System.out.println("In private constructor of JavaBasics");
    }

    static <T> int getData(T t) {
        return 0;
    }
    class PrivateInnerJavaBasics {
        JavaBasics obj = new JavaBasics(); // Outer-most class object

        private PrivateInnerJavaBasics() {
            System.out.println("In private constructor of PrivateInnerJavaBasics");
        }
        InnerJavaBasics b = new InnerJavaBasics();
    }

    class PrivateInnerJavaBasicsTwo {
        JavaBasics obj = new JavaBasics(); // Outer-most class object

        private PrivateInnerJavaBasicsTwo() {
            System.out.println("In private constructor of PrivateInnerJavaBasics");
        }
        PrivateInnerJavaBasics b = new JavaBasics.PrivateInnerJavaBasics();
    }

    static class InnerJavaBasics {
        JavaBasics obj = new JavaBasics(); // Outer-most class object
        // Non-static inner classes can't be accessed directly in the static inner classes but only via outer class object
        JavaBasics.PrivateInnerJavaBasics obj1 = obj.new PrivateInnerJavaBasics();
    }

    // Best way to define a singleton using Enum
    public enum SingletonEnum {
        INSTANCE; // Singleton

        int value; // property and getter/setter methods
        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        JavaBasics obj = new JavaBasics(); // Outer-most class object
    }
}
