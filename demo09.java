package c_DuoXianCheng;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description :阻塞队列的实现  put  take poll()
 * @date: 2022/7/12 9:19
 */
public class demo09<T> {

    private Deque<T> blockqueue=new ArrayDeque<>() ;
    private int capcatiy=5;

    private ReentrantLock lock= new ReentrantLock();
    private Condition product_wait = lock.newCondition();
    private Condition consume_wait = lock.newCondition();

    //获取任务（阻塞型）  直到拿到了任务才返回
    public T take(){
        lock.lock();
        try {
            while (blockqueue.isEmpty()){
                try {
                    consume_wait.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //说明阻塞队列里面有任务，可以取任务
            T firsttask = blockqueue.removeFirst();
            product_wait.signal();
            return firsttask;
            //System.out.println("jjj");

        }
        finally {
            lock.unlock();
        }
    }


    //获取任务（非阻塞型）  超过一定时间，还拿不到任务直接返回null
    public T poll(long timeout, TimeUnit unit){
        lock.lock();
        try {
            //将timeout时长转成纳秒
            long ns = unit.toNanos(timeout);
            while (blockqueue.isEmpty()){
                try {
                    ns=consume_wait.awaitNanos(ns);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //说明阻塞队列里面有任务，可以取任务
            T firsttask = blockqueue.removeFirst();
            product_wait.signal();
            return firsttask;

        }
        finally {
            lock.unlock();
        }
    }

    //添加任务
    public void put(T t){
        lock.lock();
        try {
            while (blockqueue.size()>capcatiy){
                product_wait.await();
            }
            //说明此时可以放任务
            blockqueue.addLast(t);
            consume_wait.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getCapcatiy(){
        lock.lock();
        try {
            return blockqueue.size();
        }finally {
            lock.unlock();
        }
    }
}
