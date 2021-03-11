package com.rzlproject.parkingmanagement.service.impl;

import com.rzlproject.parkingmanagement.entity.ParkingLotEntity;
import com.rzlproject.parkingmanagement.entity.TransactionEntity;
import com.rzlproject.parkingmanagement.entity.TypeEntity;
import com.rzlproject.parkingmanagement.model.*;
import com.rzlproject.parkingmanagement.repository.ParkingLotEntityRepository;
import com.rzlproject.parkingmanagement.repository.TransactionEntityRepository;
import com.rzlproject.parkingmanagement.repository.TypeEntityRepository;
import com.rzlproject.parkingmanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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


        Optional<TypeEntity> type = typeEntityRepository.findByTypeCarIgnoreCase(registrationRequest.getType());

        if (type.isPresent()) {

            TransactionEntity carToSave = new TransactionEntity(
                    registrationRequest.getPlatNumber(),
                    registrationRequest.getColor(),
                    type.get()

            );

            List<ParkingLotEntity> parkingLots = parkingLotEntityRepository.findByIsAvailableTrue();

            if (!parkingLots.isEmpty()) {

                ParkingLotEntity parkingLot = parkingLots.get(0);
                parkingLotEntityRepository.setAvailableToFalse(parkingLot.getIdParkingLot());

                RegistrationResponse registrationResponse = new RegistrationResponse();
                registrationResponse.setPlatNumber(registrationRequest.getPlatNumber());

                registrationResponse.setParkingLot(parkingLot.getParkingLot());
                registrationResponse.setDateEntrance(new Date());

                ParkingLotEntity lot = parkingLotEntityRepository
                        .findByParkingLotIgnoreCase(registrationResponse.getParkingLot());

                carToSave.setParkingLot(lot);
                carToSave.setDateEntrance(registrationResponse.getDateEntrance());
                transactionEntityRepository.save(carToSave);

                return registrationResponse;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public ExitVehicleResponse exitVehicle(ExitVehicleRequest exitVehicleRequest) {

        Optional<TransactionEntity> platNumber = transactionEntityRepository
                .findByPlatNumberAndIsOutFalse(exitVehicleRequest.getPlatNumber());

        if (platNumber.isPresent()) {

            TransactionEntity transaction = platNumber.get();

            ExitVehicleResponse exitVehicleResponse = new ExitVehicleResponse();
            exitVehicleResponse.setPlatNumber(transaction.getPlatNumber());
            exitVehicleResponse.setDateEntrance(transaction.getDateEntrance());
            exitVehicleResponse.setDateExit(new Date());

            TransactionEntity typeCar = transactionEntityRepository.findByTypeCarAndPlatNumber(transaction.getTypeCar(), transaction.getPlatNumber());
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

            parkingLotEntityRepository.setAvailableToTrue(transaction.getParkingLot().getIdParkingLot());

            // SAVING TO DB
            transactionEntityRepository.save(transaction);

            return exitVehicleResponse;
        }
        return null;
    }

    @Override
    public ReportVehicleByTypeResponse reportVehicleByType(ReportVehicleByTypeRequest reportVehicleByTypeRequest) {

        Optional<TypeEntity> type = typeEntityRepository
                .findByTypeCarIgnoreCase(reportVehicleByTypeRequest.getType());

        if (type.isPresent()) {
            int totalVehicle = transactionEntityRepository
                    .countTransactionEntityByTypeCar(type.get());

            return new ReportVehicleByTypeResponse(totalVehicle);
        }
        return new ReportVehicleByTypeResponse(0);
    }

    @Override
    public ReportVehicleByColorResponse reportVehicleByColor(ReportVehicleByColorRequest reportVehicleByColorRequest) {

        List<TransactionEntity> transaction = transactionEntityRepository
                .findByColorIgnoreCase(reportVehicleByColorRequest.getColor());

        List<String> platNumberByColor = new ArrayList<>();
        for (var listColor : transaction) {
            platNumberByColor.add(listColor.getPlatNumber());
        }

        return new ReportVehicleByColorResponse(platNumberByColor);
    }
}

