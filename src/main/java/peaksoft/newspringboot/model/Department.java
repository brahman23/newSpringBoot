package peaksoft.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(name = "department_seq", sequenceName = "department_seq", allocationSize = 1)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @ManyToOne(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.EAGER)
    private Hospital hospital;
    @ManyToMany(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.EAGER,mappedBy = "departments")
    private List<Doctor> doctors;
    public void addDoctor(Doctor doctor){
        if (doctors==null){
            doctors=new ArrayList<>();
        }
        doctors.add(doctor);
    }

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department(Long id, String name, Hospital hospital, List<Doctor> doctors) {
        this.id = id;
        this.name = name;
        this.hospital = hospital;
        this.doctors = doctors;
    }
}
