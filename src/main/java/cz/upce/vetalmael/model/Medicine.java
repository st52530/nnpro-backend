package cz.upce.vetalmael.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(nullable = false)
    private String code;

    @JsonIgnore
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.PERSIST)
    private List<ClinicMedicine> clinicMedicines = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "report_medicine",
            joinColumns = { @JoinColumn(name = "medicine_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") })
    private Set<Report> reports = new HashSet<>();
}