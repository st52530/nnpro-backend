package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Operation;
import cz.upce.vetalmael.model.dto.OperationDto;
import cz.upce.vetalmael.repository.OperationRepository;
import cz.upce.vetalmael.service.OperationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "operationService")
public class OperationServiceImpl implements OperationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Operation addOperation(OperationDto operationDto) {
        Operation operation = modelMapper.map(operationDto,Operation.class);
        return operationRepository.save(operation);
    }

    @Override
    public Operation editOperation(OperationDto operationDto, int idOperation) {
        Operation operation = modelMapper.map(operationDto,Operation.class);
        operation.setIdOperation(idOperation);
        return operationRepository.save(operation);
    }

    @Override
    public void removeOperation(int idOperation) {
        operationRepository.deleteById(idOperation);
    }
}
