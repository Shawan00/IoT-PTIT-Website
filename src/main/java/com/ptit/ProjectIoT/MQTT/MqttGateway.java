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
        String topic = "led/control";
        String message = ledId + "/" + action;

        mqttOutboundChannel.send(MessageBuilder.withPayload(message).setHeader("mqtt_topic", topic).build());
    }
}
