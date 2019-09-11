package com.sunwei.service;

import com.sunwei.hrm.PageRabbit9007Application;
import com.sunwei.hrm.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = PageRabbit9007Application.class)
@RunWith(SpringRunner.class)
public class Producer_topics_springboot {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSendByTopics() {
        for (int i = 0; i < 5; i++) {
            String message = "sms email inform to user" + i;
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM, "email", message);
            System.out.println("Send Message is:'" + message + "'");
        }
    }
}