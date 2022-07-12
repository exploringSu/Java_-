package c_DuoXianCheng;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.StringJoiner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description :定时任务：  本周五早上7点  执行任务。
 * @date: 2022/7/12 15:12
 */
public class demo10 {
    public static void main(String[] args) {
        ScheduledExecutorService exc = Executors.newScheduledThreadPool(1);

        //获取当前时间
        LocalDateTime now = LocalDateTime.now();
        //获取本周五 早上7点的时间
        LocalDateTime time = now.withHour(7).withMinute(0).withSecond(0).with(DayOfWeek.FRIDAY);
        long shijiancha= Duration.between(now,time).toMillis();
        long period=1000*60*60*24*7;

        //参数1：task
        //参数2：代表当前时间和目标任务时间  的时间差值长度
        //参数3：一周的间隔时间
        //参数4：时间的单位
        exc.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("正在执行任务!!!");
            }
        },shijiancha,period, TimeUnit.MILLISECONDS);


        //主线程  该运行什么 就运行什么
        System.out.println("其他事情，咋咋搞咋搞，不影响我主线程的运行");

    }
}
