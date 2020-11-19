package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Consumable;
import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.dto.MedicineDto;
import cz.upce.vetalmael.repository.MedicineRepository;
import cz.upce.vetalmael.service.MedicineService;
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

@Service(value = "medicineService")
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine addMedicine(MedicineDto medicineDto) {
        Medicine medicine = modelMapper.map(medicineDto, Medicine.class);
        return medicineRepository.save(medicine);
    }

    @Override
    public List<Medicine> importMedicine(MultipartFile file) throws IOException {
        List<Medicine> list = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 1) {
                Medicine medicine = new Medicine();

                XSSFRow row = worksheet.getRow(index);
                if(row == null || row.getCell(0) == null){
                    break;
                }
                medicine.setCode(row.getCell(0).getStringCellValue());
                medicine.setName(row.getCell(1).getStringCellValue());
                medicine.setSubstances(row.getCell(2).getStringCellValue());
                medicine.setTargetAnimals(row.getCell(3).getStringCellValue());
                medicine.setForm(row.getCell(4).getStringCellValue());
                medicine.setDateOfApproval(row.getCell(5).getDateCellValue());
                medicine.setNumberOfApproval(row.getCell(6).getStringCellValue());
                medicine.setApprovalHolder(row.getCell(7).getStringCellValue());
                medicine.setProtectionPeriod(row.getCell(8).getStringCellValue());

                list.add(medicine);
            }
        }
        return medicineRepository.saveAll(list);
    }

    @Override
    public Medicine editMedicine(MedicineDto medicineDto, int idMedicine) {
        Medicine medicine = medicineRepository.getOne(idMedicine);

        medicine.setCode(medicineDto.getCode());
        medicine.setName(medicineDto.getName());
        medicine.setSubstances(medicineDto.getSubstances());
        medicine.setTargetAnimals(medicineDto.getTargetAnimals());
        medicine.setForm(medicineDto.getForm());
        medicine.setDateOfApproval(medicineDto.getDateOfApproval());
        medicine.setNumberOfApproval(medicineDto.getNumberOfApproval());
        medicine.setApprovalHolder(medicineDto.getApprovalHolder());
        medicine.setProtectionPeriod(medicineDto.getProtectionPeriod());

        return medicineRepository.save(medicine);
    }

    @Override
    public void removeMedicine(int idMedicine) {
        medicineRepository.deleteById(idMedicine);
    }

    @Override
    public Medicine getMedicine(int idMedicine) {
        return medicineRepository.getOne(idMedicine);
    }

    @Override
    public List<Medicine> getMedicines() {
        return medicineRepository.findAll();
    }
}
