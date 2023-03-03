package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.repository.AppointmentRepository;
import peaksoft.service.AppointmentService;

import java.util.List;
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAllAppointments(Long id) {
        try {
            return appointmentRepository.getAllAppointments(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addAppointment(Appointment appointment, Long id) {
        try {
            appointmentRepository.addAppointment(appointment, id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Appointment getAppointmentById(Long id) {
        try {
            return appointmentRepository.getAppointmentById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateAppointment(Long id, Appointment appointment) {
        try {
            System.out.println("2");
                appointmentRepository.updateAppointment(id, appointment);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteAppointment(Long id, Long hospitalId) {
        try {
            appointmentRepository.deleteAppointment(id,hospitalId);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
