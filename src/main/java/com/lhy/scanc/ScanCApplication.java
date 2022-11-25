package com.lhy.scanc;

import com.lhy.scanc.Mqtt.MqttPushClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScanCApplication {

    public static void main(String[] args) throws MqttException {
        SpringApplication.run(ScanCApplication.class, args);
        MqttPushClient mqtt = MqttPushClient.getInstance();
        mqtt.subscribe("up_data");
    }

}
