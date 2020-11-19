package cz.upce.vetalmael.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clinic_medicine", uniqueConstraints={
        @UniqueConstraint(columnNames = {"medicine_id", "clinic_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicMedicine implements Serializable {

    @Id
    @GeneratedValue
    private int idClinicMedicine;

    @Column(nullable = false)
    private int quantityInStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="medicine_id", updatable = false, nullable = false)
    private Medicine medicine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clinic_id", updatable = false, nullable = false)
    private Clinic clinic;
}
