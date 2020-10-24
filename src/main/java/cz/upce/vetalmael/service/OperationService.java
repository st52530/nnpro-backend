package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Operation;
import cz.upce.vetalmael.model.dto.OperationDto;

public interface OperationService {

    Operation addOperation(OperationDto operationDto);
    Operation editOperation(OperationDto operationDto);
    void removeOperation(int idOperation);
}
