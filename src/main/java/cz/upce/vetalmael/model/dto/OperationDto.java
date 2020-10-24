package cz.upce.vetalmael.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto {

    private String name;
    private double price;
    private String type;

}