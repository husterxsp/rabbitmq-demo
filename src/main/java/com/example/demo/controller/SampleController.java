package com.example.demo.controller;

import com.example.demo.rabbitmq.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xushaopeng
 * @date 2018/12/15
 */
@RestController
public class SampleController {

    @Autowired
    MQSender mqSender;

    @RequestMapping("/")
    public String sayHello() {
        return "Hello,World!";
    }

    @RequestMapping("/mq")
    public String mq() {
        mqSender.send("hello world");
        return "hello world";
    }

    @RequestMapping("/mq/topic")
    public String topic() {
        mqSender.sendTopic("hello world");
        return "hello world";
    }

    @RequestMapping("/mq/fanout")
    public String fanout() {
        mqSender.sendFanout("hello,imooc");
        return "hello world";
    }

    @RequestMapping("/mq/header")
    public String header() {
        mqSender.sendHeader("hello,imooc");
        return "hello world";
    }
}
