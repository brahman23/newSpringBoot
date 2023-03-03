package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_seq")
    @SequenceGenerator(name = "appointment_seq", sequenceName = "appointment_seq", allocationSize = 1)
    private Long id;

    private LocalDate data;
    @ManyToOne(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.EAGER)
    private Patient patient;
    @ManyToOne(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.EAGER)
    private Department department;
    @ManyToOne(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.EAGER)
    private Doctor doctor;

    public Appointment(LocalDate data, Patient patient, Department department, Doctor doctor) {
        this.data = data;
        this.patient = patient;
        this.department = department;
        this.doctor = doctor;
    }

    public Appointment(Long id, LocalDate data) {
        this.id = id;
        this.data = data;
    }
}
