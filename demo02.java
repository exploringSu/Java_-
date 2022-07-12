package c_DuoXianCheng;

public class demo02 {
    public static void main(String[] args) throws Exception{


//        while(true){
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("2");
                }
            });

            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("3");
                }
            });

            Thread t4 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("4");
                }
            });

            Thread t5 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("5");
                }
            });
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("1");
                    Thread.currentThread().interrupt();
                    boolean a = Thread.interrupted();
                    System.out.println("a"+a);
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            });

            Thread t6 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("6");
                }
            });

            t1.start();
            t1.join();
            t2.start();
//            t2.join();
            t3.start();
//            t3.join();
            t4.start();
//            t4.join();
            t5.start();
//            t5.join();
            t6.start();
//            t6.join();
//            t1.join();
//            t2.join();
//            t3.join();
//            t4.join();
//            t5.join();
//            t6.join();
        }


//    }
}
