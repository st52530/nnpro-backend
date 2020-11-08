package cz.upce.vetalmael.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumableDto {

    private String name;
    private String code;
    private double price;
    private String nameAddition;
    private String groupType;
    private String prescriptionDesignation;
    private String unitOfMeasure;
    private String producer;
    private String countryOfOrigin;
    private String dateOfExpiration;
    private String dateOfChange;
}
