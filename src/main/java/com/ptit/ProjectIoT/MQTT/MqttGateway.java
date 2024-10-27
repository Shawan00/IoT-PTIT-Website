package com.ptit.ProjectIoT.MQTT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MqttGateway {
    @Autowired
    private final MessageChannel mqttOutboundChannel;

    @Autowired
    public MqttGateway(MessageChannel mqttOutboundChannel) {
        this.mqttOutboundChannel = mqttOutboundChannel;
    }

    public void sendLedCommand(String ledId, String action) {
        String topic = "led/control"; // Đặt topic điều khiển LED
        String message = ledId + "/" + action; // Format tin nhắn, ví dụ: "led1/on"

        // Tạo và gửi tin nhắn qua kênh MQTT outbound
        mqttOutboundChannel.send(MessageBuilder.withPayload(message).setHeader("mqtt_topic", topic).build());
    }
}
