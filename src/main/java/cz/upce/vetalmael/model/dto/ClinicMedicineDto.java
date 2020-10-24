package cz.upce.vetalmael.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicMedicineDto {

    private int idClinicMedicine;
    private int quantityInStock;
    private ClinicDto clinicDto;
    private MedicineDto medicineDto;

}
