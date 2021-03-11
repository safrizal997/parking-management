package com.rzlproject.parkingmanagement.service;

import com.rzlproject.parkingmanagement.model.*;

public interface TransactionService {


    RegistrationResponse newRegistration(RegistrationRequest registrationRequest);

    ExitVehicleResponse exitVehicle(ExitVehicleRequest exitVehicleRequest);

    ReportVehicleByTypeResponse reportVehicleByType(ReportVehicleByTypeRequest type);

    ReportVehicleByColorResponse reportVehicleByColor(ReportVehicleByColorRequest color);
}
