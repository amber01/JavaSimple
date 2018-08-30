package com.example.demo.testDemo.callBackDemo;

import javax.security.auth.callback.Callback;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 18:28 2018/8/30
 * @ Description：这是一个线程，用来单独处理下载图片的操作
 * @ Modified By：
 * @Version:
 */
public class MyThread extends Thread {

    private CallBack callBack;

    public MyThread(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void run() {
        super.run();

        // 模仿耗时操作
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
                System.out.println("图片下载中。。。（假装耗时）");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        callBack.callBackInfo("得的图片！！！");

    }
}
