package peaksoft.service;

import peaksoft.model.Patient;

import java.io.IOException;
import java.util.List;

public interface PatientService {
    List<Patient> getAllPatient(Long id);
    List<Patient> getAllPatientList(Long id);


    void addPatient(Patient patient,Long id);

    Patient getPatientById(Long Id);

    void updatePatient(Long Id, Patient patient);

    void deletePatient(Long id);

    void assignPatient(Long patientId, Long appointmentId) throws IOException;
}
