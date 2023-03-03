package peaksoft.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "hospitals")
@Getter
@Setter
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq")
    @SequenceGenerator(name = "hospital_seq", sequenceName = "hospital_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3,max = 20,message = "Name should be between 3 and 20 characters")
    private String name;
    @NotEmpty(message = "Address should not be empty")
    private String address;


    private int countPatient;

    public  void  addCount(){
        countPatient++;
    }

    public void deleteCount(){
        countPatient--;
    }


    private int countDoctor;

    public  void  addCountD(){
        countDoctor++;
    }

    public void deleteCountD(){
        countDoctor--;
    }
    @OneToMany(cascade = {DETACH,REFRESH,MERGE,REMOVE},fetch = FetchType.LAZY,mappedBy = "hospital")
    private List<Doctor> doctors;
    public void addDoctor(Doctor doctor){
        if (doctors==null){
            doctors=new ArrayList<>();
        }
        doctors.add(doctor);
    }
    @OneToMany(cascade = {DETACH,REFRESH,MERGE,REMOVE},fetch = FetchType.LAZY,mappedBy = "hospital")
    private List<Department> departments;
    public void addDepartment(Department department){
        if (departments==null){
            departments=new ArrayList<>();
        }
        departments.add(department);
    }
    @OneToMany(cascade = {DETACH,REFRESH,MERGE,REMOVE},fetch = FetchType.LAZY)
    private List<Appointment> appointments;
    public void addAppointment(Appointment appointment){
        if (appointments==null){
            appointments=new ArrayList<>();
        }
        appointments.add(appointment);
    }
    @OneToMany(cascade = {DETACH,REFRESH,MERGE,REMOVE},fetch = FetchType.LAZY,mappedBy = "hospital")
    private List<Patient> patients;
    public void addPatient(Patient patient){
        if (patients==null){
            patients=new ArrayList<>();
        }
        patients.add(patient);
    }

    public Hospital(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
