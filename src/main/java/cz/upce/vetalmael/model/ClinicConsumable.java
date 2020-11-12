package cz.upce.vetalmael.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clinic_consumable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicConsumable implements Serializable {

    @Id
    @GeneratedValue
    private int idClinicConsumable;

    @Column(nullable = false)
    private int quantityInStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="consumable_id", updatable = false)
    private Consumable consumable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clinic_id", updatable = false)
    private Clinic clinic;
}
