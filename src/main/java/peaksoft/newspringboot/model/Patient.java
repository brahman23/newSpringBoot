package peaksoft.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.model.gender.Gender;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq")
    @SequenceGenerator(name = "patient_seq", sequenceName = "patient_seq", allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    @NotEmpty(message = "FirstName should not be empty")
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "LastName should not be empty")
    private String lastName;
    @Column(name = "phone_number")
    @NotEmpty(message = "PhoneNumber should not be empty")
    @Size(max = 13,min = 9,message = "number should between 9 - 13 characters")
    private String phoneNumber;

    private Gender gender;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @ManyToOne(cascade = {DETACH,MERGE,REFRESH},fetch = FetchType.EAGER)
    private Hospital hospital;

    @OneToMany(cascade = {DETACH,MERGE,REFRESH,REMOVE},fetch = FetchType.EAGER,mappedBy = "patient")
    private List<Appointment> appointments;
    public void addAppointment(Appointment appointment){
        if (appointments==null){
            appointments = new ArrayList<>();
        }
        appointments.add(appointment);
    }

    public Patient(Long id, String firstName, String lastName, String phoneNumber, Gender gender, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
    }
}
