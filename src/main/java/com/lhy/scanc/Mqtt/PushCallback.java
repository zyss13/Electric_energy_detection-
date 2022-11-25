package com.lhy.scanc.Mqtt;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class PushCallback implements MqttCallback {
    private int saveKey=0;


    private ObjectMapper mapper = new ObjectMapper();
    public static PushCallback pushCallback;
    public static MqttPushClient mqttPushClient;
    public static Map map;


    @PostConstruct
    public void init() {
        pushCallback = this;



    }

    @Override
    public void connectionLost(Throwable cause) {

        System.out.println("连接断开，可以做重连");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload());
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
        System.out.println("接收消息内容 : " + payload);
        map = mapper.readValue(payload, HashMap.class);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println(token);
        try {
            System.out.println(token.getMessage());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public PushCallback(){};

    public PushCallback(int saveKey){
        this.saveKey = saveKey;

    }
    public PushCallback(MqttPushClient mqttPushClient){
        this.mqttPushClient=mqttPushClient;

    }

    public int getSaveKey() {
        return saveKey;
    }

    public void setSaveKey(int saveKey) {
        this.saveKey = saveKey;
    }
}

