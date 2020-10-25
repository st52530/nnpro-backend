package cz.upce.vetalmael.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "consumable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consumable implements Serializable {

    @Id
    @GeneratedValue
    private int idConsumable;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private double price;

    @JsonIgnore
    @OneToMany(mappedBy = "consumable", cascade = CascadeType.PERSIST)
    private List<ClinicConsumable> clinicConsumables = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "consumables", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Report> reports = new HashSet<>();
}