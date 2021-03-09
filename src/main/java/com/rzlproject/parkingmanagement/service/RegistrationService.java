package com.rzlproject.parkingmanagement.service;

import com.rzlproject.parkingmanagement.entity.ParkingLotEntity;
import com.rzlproject.parkingmanagement.model.RegistrationRequest;
import com.rzlproject.parkingmanagement.model.RegistrationResponse;

public interface RegistrationService {


    RegistrationResponse newRegistration(RegistrationRequest registrationRequest, ParkingLotEntity parkingLotEntity);

}
