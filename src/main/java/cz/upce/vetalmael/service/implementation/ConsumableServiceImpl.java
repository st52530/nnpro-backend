package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Consumable;
import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.dto.ConsumableDto;
import cz.upce.vetalmael.repository.ConsumableRepository;
import cz.upce.vetalmael.service.ConsumableService;
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

@Service(value = "consumableService")
public class ConsumableServiceImpl implements ConsumableService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ConsumableRepository consumableRepository;

    @Override
    public Consumable addConsumable(ConsumableDto consumableDto) {
        Consumable consumable = modelMapper.map(consumableDto, Consumable.class);
        return consumableRepository.save(consumable);
    }

    @Override
    public List<Consumable> importConsumables(MultipartFile file) throws IOException {
        List<Consumable> list = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        System.out.println(worksheet.getPhysicalNumberOfRows());
        for (int index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index >= 1) {
                Consumable consumableDto = new Consumable();

                XSSFRow row = worksheet.getRow(index);
                if(row == null || row.getCell(0) == null){
                    break;
                }
                consumableDto.setCode(row.getCell(0).getRawValue());
                consumableDto.setName(row.getCell(1).getRawValue());
                consumableDto.setNameAddition(row.getCell(2).getRawValue());
                consumableDto.setGroupType(row.getCell(3).getRawValue());
                consumableDto.setPrescriptionDesignation(row.getCell(4).getRawValue());
                consumableDto.setUnitOfMeasure(row.getCell(5).getRawValue());
                consumableDto.setProducer(row.getCell(6).getRawValue());
                consumableDto.setCountryOfOrigin(row.getCell(7).getRawValue());
                consumableDto.setPrice(row.getCell(8).getNumericCellValue());
                consumableDto.setDateOfExpiration(row.getCell(9).getDateCellValue());
                consumableDto.setDateOfChange(row.getCell(10).getDateCellValue());
                list.add(consumableDto);
           }
        }
        return consumableRepository.saveAll(list);
    }


    @Override
    public Consumable editConsumable(ConsumableDto consumableDto, int idConsumable) {
        Consumable consumable = modelMapper.map(consumableDto, Consumable.class);
        consumable.setIdConsumable(idConsumable);
        return consumableRepository.save(consumable);
    }

    @Override
    public void removeConsumable(int idConsumable) {
        consumableRepository.deleteById(idConsumable);
    }

    @Override
    public Consumable getConsumable(int idConsumable) {
        return consumableRepository.getOne(idConsumable);
    }

    @Override
    public List<Consumable> getConsumables() {
        return consumableRepository.findAll();
    }
}
