package com.lhy.scanc.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhy.scanc.Mqtt.MqttPushClient;
import com.lhy.scanc.Mqtt.PushCallback;
import com.lhy.scanc.Utils.Result;
import com.lhy.scanc.Utils.ResultUtil;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mqtt")
public class MqttController {
    static MqttPushClient mq;

    @Autowired
    ObjectMapper objectMapper;
    @PostMapping("/publish")
    public Result publish(@RequestBody String data) throws JsonProcessingException {
        Map<String,String> map = objectMapper.readValue(data, HashMap.class);
        String topic = map.get("topic");
        String msg  = map.get("msg");
        try {
            if (mq == null){
                mq = new MqttPushClient();
            }
            mq.publish(topic,msg);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.fail("publish fail");

        }
        return ResultUtil.success("publish success");

    }

    @GetMapping("/get")
    public Result getData(){
        return ResultUtil.success(PushCallback.map);
    }
}
