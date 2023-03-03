package peaksoft.service;

import peaksoft.model.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments(Long id);

    void addAppointment(Appointment appointment,Long id);

    Appointment getAppointmentById(Long id);

    void updateAppointment(Long id, Appointment appointment);

    void deleteAppointment(Long id,Long hospitalId);
}
