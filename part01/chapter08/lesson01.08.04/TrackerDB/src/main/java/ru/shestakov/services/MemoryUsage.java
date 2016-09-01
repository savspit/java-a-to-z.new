package ru.shestakov.services;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        import java.lang.ref.SoftReference;
        import java.lang.ref.WeakReference;

public class MemoryUsage {
    private static final Logger log = LoggerFactory.getLogger(MemoryUsage.class);

    public static class User {
        public String name;
        public int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize");
        }

    }

    public static void main(String[] args) {

        System.out.println("start");
        info();
        User user = new User("test", 10);
        User user1 = new User("test", 20);
        User user2 = new User("test", 30);
        User user3 = new User("test", 40);
        System.out.println(user);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        user = null;
        user1 = null;
        user2 = null;
        user3 = null;
        System.gc();
        System.out.println("finish");
        info();

    }

    public static void info() {
        int mb = 1024*1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] #####");

        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb);

        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);

        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);
    }
}
