package c_DuoXianCheng;

public class demo03 {
    public static void main(String[] args) {
        Thread t1= new Thread(new Runnable() {
            @Override
            public void run() {
                shuijiao();
                System.out.println(1);
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });

        t1.start();
        System.out.println(Thread.currentThread().isInterrupted());
        t1.interrupt();
        System.out.println(Thread.currentThread().isInterrupted());


    }

    public static void shuijiao()  {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("异常咯");
            e.printStackTrace();
            System.out.println("3333333333333333");
        }
    }
}
