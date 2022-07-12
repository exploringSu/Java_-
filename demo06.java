package c_DuoXianCheng;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @description :使用lock的condition精准唤醒
 * @date: 2022/7/11 16:05
 */
public class demo06 {
    static ReentrantLock lock=new ReentrantLock();
    static boolean flagA=true;
    static boolean flagB=false;
    static boolean flagC=false;
    static Condition conditionA = lock.newCondition();
    static Condition conditionB = lock.newCondition();
    static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {


        new Thread(()->{
            lock.lock();
            try{
                while (flagB==false){
                    conditionB.await();
                }
                //falgsB 等于true了

                System.out.println("我是线程B   打印ABC");
                flagC=true;
                conditionC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            lock.lock();
            try {
                while (flagC==false){
                    conditionC.await();
                }
                System.out.println("我是线程C  我打印ABC");
                flagA=true;
                conditionA.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();



        new Thread(()->{
            lock.lock();
            try{
                while (flagA==false){
                    conditionA.await();
                }
                //到这说明线程a被唤醒了，应该打印了
                System.out.println("我是A线程  打印ABC");
                //把B的条件改对
                flagB=true;
                conditionB.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
