package application.controller;

import application.model.PrimeNumberResult;
import application.service.PrimeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import application.repository.MockRepositoryStateful;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class DataController {

    @Autowired
    PrimeNumberService primeNumberService;

    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public PrimeNumberResult get(@RequestParam("prime-number")double primeNumber){
        System.out.println(primeNumber);
        return primeNumberService.calcNumberResult(primeNumber);
    }


}
