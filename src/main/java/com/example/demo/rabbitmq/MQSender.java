package com.example.demo.rabbitmq;

import com.example.demo.util.BeanStringConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author xushaopeng
 * @date 2018/10/05
 */
@Service
public class MQSender {
    private static Logger log = LoggerFactory.getLogger(MQSender.class);

    // 操作消息队列的工具方法
    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(Object message) {
        // 用自定义的工具方法来将消息转换成字符串发送出去
        String msg = BeanStringConvert.bean2String(message);
        log.info("发送消息" + msg);
        amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
    }

    public void sendTopic(Object message) {
        String msg = BeanStringConvert.bean2String(message);
        log.info("发送topic消息" + msg);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", message + "1");
        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", message + "2");
    }

    public void sendFanout(Object message) {
        String msg = BeanStringConvert.bean2String(message);
        log.info("发送fanout消息" + msg);

        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "", msg);
    }

    public void sendHeader(Object message) {

        String msg = BeanStringConvert.bean2String(message);
        log.info("发送fanout消息" + msg);

        MessageProperties properties = new MessageProperties();
        properties.setHeader("header1", "value1");
        properties.setHeader("header2", "value2");

        Message obj = new Message(msg.getBytes(), properties);

        amqpTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE, "", obj);
    }

}
