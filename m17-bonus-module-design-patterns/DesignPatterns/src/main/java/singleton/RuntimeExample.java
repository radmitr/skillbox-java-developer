package singleton;

public class RuntimeExample {

    public static void main(String[] args) {
//        Runtime runtime = new Runtime(); // error: 'Runtime()' has private access in 'java.lang.Runtime'
        Runtime runtime = Runtime.getRuntime();
        int coreCount = runtime.availableProcessors();
        System.out.println(coreCount);
    }
}
