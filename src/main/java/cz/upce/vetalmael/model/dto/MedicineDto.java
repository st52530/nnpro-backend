package cz.upce.vetalmael.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto {

    private String name;
    private String code;
    private String substances;
    private String targetAnimals;
    private String form;
    private Date dateOfApproval;
    private String numberOfApproval;
    private String approvalHolder;
    private String protectionPeriod;
    private String type;
    private String packageSize;
}
