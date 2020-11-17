package cz.upce.vetalmael.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "medicine")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicine implements Serializable {

    @Id
    @GeneratedValue
    private int idMedicine;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String substances;

    @Column(nullable = false)
    private String targetAnimals;

    @Column
    private String form;

    @Column
    private Date dateOfApproval;

    @Column
    private String numberOfApproval;

    @Column
    private String approvalHolder;

    @Column
    private String protectionPeriod;

    @Column
    private String type;

    @Column
    private String packageSize;


    @JsonIgnore
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.PERSIST)
    private List<ClinicMedicine> clinicMedicines = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "medicines", cascade = CascadeType.PERSIST)
    private Set<Report> reports = new HashSet<>();
}