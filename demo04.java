package c_DuoXianCheng;

public class demo04 {
    volatile static boolean a;
    volatile Boolean c=true;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                a=false;
            }
        }).start();

    }


}
