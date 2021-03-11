package com.rzlproject.parkingmanagement.service;

import com.rzlproject.parkingmanagement.model.ExitVehicleRequest;
import com.rzlproject.parkingmanagement.model.ExitVehicleResponse;
import com.rzlproject.parkingmanagement.model.RegistrationRequest;
import com.rzlproject.parkingmanagement.model.RegistrationResponse;

public interface TransactionService {


    RegistrationResponse newRegistration(RegistrationRequest registrationRequest);

    ExitVehicleResponse exitVehicle (ExitVehicleRequest exitVehicleRequest);

}
