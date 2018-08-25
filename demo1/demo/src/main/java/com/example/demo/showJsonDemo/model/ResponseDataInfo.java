package com.example.demo.showJsonDemo.model;

import lombok.Data;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 11:38 2018/8/25
 * @ Description：统一处理返回的数据对象
 * @ Modified By：
 * @Version:
 */

@Data
public class ResponseDataInfo {

    public int code;

    public String message;

    public Object data;
}
