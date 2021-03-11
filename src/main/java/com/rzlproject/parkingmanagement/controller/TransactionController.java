package com.rzlproject.parkingmanagement.controller;

import com.rzlproject.parkingmanagement.entity.TransactionEntity;
import com.rzlproject.parkingmanagement.entity.TypeEntity;
import com.rzlproject.parkingmanagement.model.ExitVehicleRequest;
import com.rzlproject.parkingmanagement.model.ExitVehicleResponse;
import com.rzlproject.parkingmanagement.model.RegistrationRequest;
import com.rzlproject.parkingmanagement.model.RegistrationResponse;
import com.rzlproject.parkingmanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(path = "/api/parking-management/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(path = "/registration")
    public ResponseEntity<?> newRegistration(@RequestBody RegistrationRequest registrationRequest) {

        RegistrationResponse registrationResponse = transactionService.newRegistration(registrationRequest);
        if ( registrationResponse!= null) {
            return new ResponseEntity<>(registrationResponse, HttpStatus.CREATED);
        }

        Map<String, Object> responseDefault = new HashMap<>();

        responseDefault.put("status", HttpStatus.NOT_FOUND.getReasonPhrase());
        responseDefault.put("message", "Parkir tidak tersedia");
        responseDefault.put("timestamp", new Date());

        return new ResponseEntity<>(responseDefault, HttpStatus.NOT_FOUND);
    }

//    public RegistrationResponse newRegistration(@RequestBody RegistrationRequest registrationRequest){
//        return transactionService.newRegistration(registrationRequest);
//    }

    @PostMapping(path = "/transaction")
    public ExitVehicleResponse exitVehicle (@RequestBody ExitVehicleRequest exitVehicleRequest){
        return transactionService.exitVehicle(exitVehicleRequest);
    }


}