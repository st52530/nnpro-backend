package cz.upce.vetalmael.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clinic_medicine")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicMedicine implements Serializable {

    @Id
    @GeneratedValue
    private int idClinicMedicine;

    @Column(nullable = false)
    private int quantityInStock;

    @ManyToOne
    @JoinColumn(name="medicine_id", updatable = false, nullable = false)
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name="clinic_id", updatable = false, nullable = false)
    private Clinic clinic;
}
