package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.Operation;
import cz.upce.vetalmael.model.dto.OperationDto;
import cz.upce.vetalmael.repository.OperationRepository;
import cz.upce.vetalmael.service.OperationService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Operation> importOperations(MultipartFile file) throws IOException {
        List<Operation> list = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 1) {
                Operation operation = new Operation();

                XSSFRow row = worksheet.getRow(index);
                if(row == null || row.getCell(0) == null){
                    break;
                }
                operation.setType(row.getCell(0).getStringCellValue());
                operation.setName(row.getCell(1).getStringCellValue());
                operation.setDescription(row.getCell(2).getStringCellValue());
                operation.setLength(row.getCell(3).getStringCellValue());
                operation.setPrice(row.getCell(4).getNumericCellValue());
                operation.setNote(row.getCell(5).getStringCellValue());
                operation.setTargetAnimals(row.getCell(6).getStringCellValue());


                list.add(operation);
            }
        }
        return operationRepository.saveAll(list);
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

    @Override
    public List<Operation> getOperations() {
        return operationRepository.findAll();
    }

    @Override
    public Operation getOperation(int idOperation) {
        return operationRepository.getOne(idOperation);
    }
}
