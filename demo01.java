package c_DuoXianCheng;

public class demo01 {
    static int flag=1;

    public static void main(String[] args) throws Exception{


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(flag==1){
                    for(int i=0;i<3;i++){
                        System.out.println(Thread.currentThread().getName()+"   ABC");
                    }

                    flag=2;
                }
            }
        });
        t1.setName("线程1");


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(flag==2){
                    for(int i=0;i<2;i++){
                        System.out.println(Thread.currentThread().getName()+"   ABC");
                    }

                    flag=3;
                }
            }
        });
        t2.setName("线程2");



        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                if(flag==3){
                    for(int i=0;i<1;i++){
                        System.out.println(Thread.currentThread().getName()+"   ABC");
                    }

                    flag=1;
                }
            }
        });
        t3.setName("线程3");


            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();



    }
}
