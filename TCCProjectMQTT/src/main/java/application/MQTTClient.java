package application;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

public class MQTTClient implements MqttCallbackExtended {

    private final String serverURI;
    private MqttClient client;
    private final MqttConnectOptions mqttOptions;

    public MQTTClient(String serverURI) {

        this.serverURI = serverURI;
        mqttOptions = new MqttConnectOptions();
        mqttOptions.setMaxInflight(200);
        mqttOptions.setConnectionTimeout(3);
        mqttOptions.setKeepAliveInterval(10);
        mqttOptions.setAutomaticReconnect(true);
        mqttOptions.setCleanSession(false);

    }

    public IMqttToken subscribe(IMqttMessageListener MQTTMenager, String topic) {
        try {
            return client.subscribeWithResponse(topic, 0, MQTTMenager);
        } catch (MqttException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void startServer() {
        try {
            System.out.println("try connecting broker MQTT in " + serverURI);
            client = new MqttClient(serverURI,
                    String.format("cliente_java_%d",
                            System.currentTimeMillis()),
                    new MqttDefaultFilePersistence(System.getProperty("java.io.tmpdir")));
            client.setCallback(this);
            client.connect(mqttOptions);
        } catch (MqttException ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void publish(String topic, byte[] payload) {
        try {
            if (client.isConnected()) {
                client.publish(topic, payload, 0, false);
                System.out.println(String.format("topic %s publish. %dB", topic, payload.length));
            }
        } catch (MqttException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void connectComplete(boolean b, String s) {
        System.out.println("Connect MQTT");
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
