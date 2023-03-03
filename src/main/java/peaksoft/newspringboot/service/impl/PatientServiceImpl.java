package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Patient;
import peaksoft.model.exeptiion.MyExeption;
import peaksoft.repository.PatientRepository;
import peaksoft.service.PatientService;

import java.io.IOException;
import java.util.List;
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentServiceImpl appointmentService;
    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, AppointmentServiceImpl appointmentService) {
        this.patientRepository = patientRepository;
        this.appointmentService = appointmentService;
    }

    @Override
    public List<Patient> getAllPatient(Long id) {
        try {
            return patientRepository.getAllPatient(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatientList(Long id) {
        try {
            return patientRepository.getAllPatientList(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addPatient(Patient patient, Long id) {
        try {
            while (true){
                if (patient.getPhoneNumber().length() == 13
                        && patient.getPhoneNumber().charAt(0) == '+'
                        && patient.getPhoneNumber().charAt(1) == '9'
                        && patient.getPhoneNumber().charAt(2) == '9'
                        && patient.getPhoneNumber().charAt(3) == '6') {
                    patientRepository.addPatient(patient, id);
                    break;
                }
                else
                    throw new MyExeption("number");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Patient getPatientById(Long Id) {
        try {
            return patientRepository.getPatientById(Id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updatePatient(Long Id, Patient patient) {
        try {
            patientRepository.updatePatient(Id, patient);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deletePatient(Long id) {
        try {
            Patient patient = getPatientById(id);
            for (Appointment a:patient.getAppointments()) {
                a.getPatient().setAppointments(null);
                appointmentService.deleteAppointment(a.getId(),a.getPatient().getHospital().getId());



            }
            patientRepository.deletePatient(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void assignPatient(Long patientId, Long appointmentId) throws IOException {
        try {
            patientRepository.assignPatient(patientId, appointmentId);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
