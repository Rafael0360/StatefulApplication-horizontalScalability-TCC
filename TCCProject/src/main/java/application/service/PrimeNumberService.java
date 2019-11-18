package application.service;

import application.model.PrimeNumberResult;
import application.repository.MockRepositoryStateful;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PrimeNumberService {

    @Autowired
    private MockRepositoryStateful mockRepositoryStateful;

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
        return mockRepositoryStateful.add(new PrimeNumberResult(result, isPrime, new Date()));
    }
}
