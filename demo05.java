package c_DuoXianCheng;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class demo05 {
    public static void main(String[] args) {
        ExecutorService exc = Executors.newSingleThreadExecutor();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("b");
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("C");
            }
        });

        for(int i=0;i<5;i++) {
            exc.submit(t1);
            exc.submit(t2);
            exc.submit(t3);
        }
        exc.shutdown();

    }
}
