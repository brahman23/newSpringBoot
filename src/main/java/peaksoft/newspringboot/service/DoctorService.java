package peaksoft.service;

import peaksoft.model.Doctor;

import java.io.IOException;
import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors(Long id);
    List<Doctor> getAllDoctorsList(Long id);

    void addDoctors(Doctor doctor,Long id);

    Doctor getDoctorById(Long doctorId);

    void updateDoctor(Long Id, Doctor doctor);

    void deleteDoctor(Long id);

    void assignDoctor(Long doctorId, Long appointmentId) throws IOException;
}
