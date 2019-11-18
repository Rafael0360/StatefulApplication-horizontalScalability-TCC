package application.service;

import application.ListenerMQTT;
import application.MQTTClient;
import application.model.PrimeNumberResult;
import application.repository.MockRepositoryStateful;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PrimeNumberService {

    MQTTClient mqttClient;
    private static final String TOPIC = "br/com/rafaeloliveira/tcc";

    @Autowired
    private MockRepositoryStateful mockRepositoryStateful;

    public PrimeNumberService() {
        mqttClient = new MQTTClient("tcp://broker.mqttdashboard.com:1883");
        mqttClient.startServer();
        new Thread(() -> {
            new ListenerMQTT(mqttClient, TOPIC);
        }).start();
    }

    public PrimeNumberResult calcNumberResult(double input){
        PrimeNumberResult result = getPrimeNumber(input);
        if(result == null){
            boolean isPrime = isPrimeService(input);
            return savePrimeNumber(input, isPrime);
        }
        return result;
    }

    public PrimeNumberResult getPrimeNumber(double input){
        return mockRepositoryStateful.getByPrime(input);
    }

    private boolean isPrimeService(double input){
        for (double i = 2; i < input; i++){
            if(input%i == 0){
                return false;
            }
        }
        return true;
    }

    private PrimeNumberResult savePrimeNumber(double result, boolean isPrime){

        PrimeNumberResult primeNumberResult =
                mockRepositoryStateful.add(new PrimeNumberResult(result, isPrime, new Date()));
        mqttClient.publish(TOPIC, String.valueOf(primeNumberResult.toString()).getBytes());
        return primeNumberResult;
    }
}
