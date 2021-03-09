package com.rzlproject.parkingmanagement.service.impl;

import com.rzlproject.parkingmanagement.entity.CarEntity;
import com.rzlproject.parkingmanagement.entity.ParkingLotEntity;
import com.rzlproject.parkingmanagement.entity.TypeEntity;
import com.rzlproject.parkingmanagement.model.RegistrationRequest;
import com.rzlproject.parkingmanagement.model.RegistrationResponse;
import com.rzlproject.parkingmanagement.repository.CarEntityRepository;
import com.rzlproject.parkingmanagement.repository.ParkingLotEntityRepository;
import com.rzlproject.parkingmanagement.repository.TypeEntityRepository;
import com.rzlproject.parkingmanagement.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    CarEntityRepository carEntityRepository;

    @Autowired
    ParkingLotEntityRepository parkingLotEntityRepository;

    @Autowired
    TypeEntityRepository typeEntityRepository;


    @Override
    @Transactional
    public RegistrationResponse newRegistration(RegistrationRequest registrationRequest, ParkingLotEntity parkingLotEntity) {


        TypeEntity type = typeEntityRepository.findByTypeCarIgnoreCase(registrationRequest.getType());
        CarEntity carToSave = new CarEntity(
                registrationRequest.getPlatNumber(),
                registrationRequest.getColor(),
                type
        );

        List<ParkingLotEntity> parkingLots = parkingLotEntityRepository.findByIsAvailableTrue();

        if (!parkingLots.isEmpty()) {

            ParkingLotEntity parkingLot = parkingLots.get(0);
            parkingLotEntityRepository.setAvailableToFalse(parkingLot.getIdParkingLot());

            carEntityRepository.save(carToSave);

            RegistrationResponse registrationResponse = new RegistrationResponse();
            registrationResponse.setPlatNumber(registrationRequest.getPlatNumber());
            registrationResponse.setParkingLot(parkingLot.getParkingLot());

            registrationResponse.setDateEntrance(new Date());
            // Save to DB transaction
        /*
        id,
        platnomor:  registrationRequest.getPlatNumber(),
        tglmasuk,: registration.getDateEntrance()
        tglkeluar,: null
        jumlah bayar : type.getPrice()
        isKeluar: boolean (true/false)

         */

            return registrationResponse;
        }
        return null;
    }


}
