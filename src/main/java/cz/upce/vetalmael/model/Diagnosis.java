package cz.upce.vetalmael.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diagnosis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis implements Serializable {

    @Id
    @GeneratedValue
    private int idDiagnosis;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "diagnosis", cascade = CascadeType.PERSIST)
    private List<Report> reports = new ArrayList<>();
}
