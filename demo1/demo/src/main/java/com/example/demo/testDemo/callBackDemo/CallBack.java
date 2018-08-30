package com.example.demo.testDemo.callBackDemo;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 18:26 2018/8/30
 * @ Description：这里我们假设一个场景：A需要在网上下载一张图片，但是A并不知道下载这张图片需要多少时间，
 *                于是A开了一个线程B去下载这张图片，并且B下载完后将图片给A。
 * @ Modified By：
 * @Version:
 */
public interface CallBack {
    public void callBackInfo(String pricture);
}
