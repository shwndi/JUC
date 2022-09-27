CountDownLatch：减法器
 * 1、new CountDownLatch（100）初始化
 * 2、o.countDown()做减法每次减一
 * 3、o.await()等待归0，主线程才能继续跑，否则等待着。
CyclicBarrier： 加法器，运行n次后执行预定代码
 * 加法器
 * 1、初始值，和任务函数
 * 2、 每开启一个线程，机会自增1
 * 3、直到达到初始值后停下；同时执行预设的线程。
 * 注：不会影响CyclicBarrier之外的程序执行。
Semaphore：信号控制器，到达数量，执行后续代码
 * 限定数量：
 * acquire()是计数器
 * release()监控数量，到达后释放阻塞线程