package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.Operation;
import cz.upce.vetalmael.model.dto.OperationDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface OperationService {

    Operation addOperation(OperationDto operationDto);
    List<Operation> importOperations(MultipartFile file) throws IOException;
    Operation editOperation(OperationDto operationDto, int idOperation);
    void removeOperation(int idOperation);
}
