package cz.upce.vetalmael.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto {

    private String name;
    private double price;
    private String type;
    private String description;
    private String length;
    private String note;
    private String targetAnimals;

}