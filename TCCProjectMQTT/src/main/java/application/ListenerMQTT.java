package application;

import application.model.PrimeNumberResult;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListenerMQTT implements IMqttMessageListener {

    public ListenerMQTT(MQTTClient mqttClient, String topic){
        mqttClient.subscribe(this, topic);
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("\tmsg: " + new String(mqttMessage.getPayload()));
    }
}
