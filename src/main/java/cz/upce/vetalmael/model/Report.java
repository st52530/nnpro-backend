package cz.upce.vetalmael.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report implements Serializable {

    @Id
    @GeneratedValue
    private int idReport;

    @Column
    private String textDescription;

    @Column
    private String textDiagnosis;

    @Column
    private String textRecommendation;

    @Enumerated
    @Column(nullable = false)
    private ReportState reportState;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="animal_id", nullable = false, updatable = false)
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User veterinary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="diagnosis_id")
    private Diagnosis diagnosis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="operation_id")
    private Operation operation;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "report_medicine",
            joinColumns = { @JoinColumn(name = "report_id") },
            inverseJoinColumns = { @JoinColumn(name = "medicine_id") })
    private Set<Medicine> medicines = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "report_consumable",
            joinColumns = { @JoinColumn(name = "report_id") },
            inverseJoinColumns = { @JoinColumn(name = "consumable_id") })
    private Set<Consumable> consumables = new HashSet<>();

}
