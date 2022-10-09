import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadFactory;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        System.out.println("==========>");
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("==========>"+Thread.currentThread().getName());
            }
        };
        MyThreadFactory threadFactory = new MyThreadFactory();
        threadFactory.newThread(runnable).start();

    }

    private static class MyThreadFactory implements ThreadFactory{

        /**
         * Constructs a new {@code Thread}.  Implementations may also initialize
         * priority, name, daemon status, {@code ThreadGroup}, etc.
         *
         * @param r a runnable to be executed by new thread instance
         * @return constructed thread, or {@code null} if the request to
         * create a thread is rejected
         */
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(false);
            thread.setName("自定义线程" + r.getClass().getName());

            return thread;
        }

    }
}
