package com.rzlproject.parkingmanagement.service.impl;

import com.rzlproject.parkingmanagement.entity.TransactionEntity;
import com.rzlproject.parkingmanagement.entity.ParkingLotEntity;
import com.rzlproject.parkingmanagement.entity.TypeEntity;
import com.rzlproject.parkingmanagement.model.ExitVehicleRequest;
import com.rzlproject.parkingmanagement.model.ExitVehicleResponse;
import com.rzlproject.parkingmanagement.model.RegistrationRequest;
import com.rzlproject.parkingmanagement.model.RegistrationResponse;
import com.rzlproject.parkingmanagement.repository.TransactionEntityRepository;
import com.rzlproject.parkingmanagement.repository.ParkingLotEntityRepository;
import com.rzlproject.parkingmanagement.repository.TypeEntityRepository;
import com.rzlproject.parkingmanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionEntityRepository transactionEntityRepository;

    @Autowired
    ParkingLotEntityRepository parkingLotEntityRepository;

    @Autowired
    TypeEntityRepository typeEntityRepository;


    @Override
    @Transactional
    public RegistrationResponse newRegistration(RegistrationRequest registrationRequest) {


        TypeEntity type = typeEntityRepository.findByTypeCarIgnoreCase(registrationRequest.getType());
        TransactionEntity carToSave = new TransactionEntity(
                registrationRequest.getPlatNumber(),
                registrationRequest.getColor(),
                type

        );

        List<ParkingLotEntity> parkingLots = parkingLotEntityRepository.findByIsAvailableTrue();

        if (!parkingLots.isEmpty()) {

            ParkingLotEntity parkingLot = parkingLots.get(0);
            parkingLotEntityRepository.setAvailableToFalse(parkingLot.getIdParkingLot());

            RegistrationResponse registrationResponse = new RegistrationResponse();
            registrationResponse.setPlatNumber(registrationRequest.getPlatNumber());
            registrationResponse.setParkingLot(parkingLot.getParkingLot());
            registrationResponse.setDateEntrance(new Date());

            carToSave.setDateEntrance(registrationResponse.getDateEntrance());
            transactionEntityRepository.save(carToSave);
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

    @Override
    public ExitVehicleResponse exitVehicle(ExitVehicleRequest exitVehicleRequest) {

        Optional<TransactionEntity> platNumber = transactionEntityRepository
                .findByPlatNumberAndIsOutFalse(exitVehicleRequest.getPlatNumber());

        if (platNumber.isPresent()) {

            TransactionEntity transaction = platNumber.get();

            ExitVehicleResponse exitVehicleResponse = new ExitVehicleResponse();
            exitVehicleResponse.setPlatNumber(transaction.getPlatNumber());
            exitVehicleResponse.setDateEntrance(transaction.getDateEntrance());
            exitVehicleResponse.setDateExit(new Date());

            TransactionEntity typeCar = transactionEntityRepository.findByTypeCar(transaction.getTypeCar());
            Integer price = typeCar.getTypeCar().getPrice();

            long parkingTime = exitVehicleResponse.getDateExit().getTime() - exitVehicleResponse.getDateEntrance().getTime();
            int iParkingTimeOnHour = (int) parkingTime / (60 * 60 * 1000);

            int priceAdd = (price * 20) / 100;
            Integer payment = (iParkingTimeOnHour * priceAdd);
            Integer paidAmounts = price + payment;

            exitVehicleResponse.setPaidAmount(paidAmounts);

            //set transaction to update
            transaction.setDateExit(exitVehicleResponse.getDateExit());
            transaction.setOut(true);
            transaction.setPaidAmount(exitVehicleResponse.getPaidAmount());

            // SAVING TO DB
            transactionEntityRepository.save(transaction);

            return exitVehicleResponse;
        }
        return null;
    }
}


//        TransactionEntity getPlatNumber = carEntityRepository.findByPlatNumber(registrationRequest.getPlatNumber());
//
//        List<TransactionEntity1> vehicleStatus = transactionEntityRepository.findByOutIsFalse();
//
//        if (vehicleStatus.isEmpty()) {
//
//            ExitVehicleResponse exitVehicleResponse = new ExitVehicleResponse();
//            exitVehicleResponse.setPlatNumber(getPlatNumber.getPlatNumber());
//            exitVehicleResponse.setDateEntrance(registrationResponse.getDateEntrance());
//            exitVehicleResponse.setDateExit(new Date());
//
//
//            TypeEntity typeCar = typeEntityRepository.findByTypeCarIgnoreCase(typeEntity.getPrice());
//            Integer price = typeCar.getPrice();
//
//            long parkingTime = exitVehicleResponse.getDateExit().getTime() - exitVehicleResponse.getDateEntrance().getTime();
//            int parkingTimeOnHour = (int) parkingTime / 24;
//
//            Integer payment = (parkingTimeOnHour * 5000);
//            Integer paidAmounts =price + payment;
//
//            exitVehicleResponse.setPaidAmount(paidAmounts);
//
//            TransactionEntity1 status = new TransactionEntity1();
//            transactionEntityRepository.setIsOutToTrue(status.getIdTransaction());
//            return null;
//        }
//    }

