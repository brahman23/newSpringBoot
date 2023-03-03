package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Patient;
import peaksoft.service.AppointmentService;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.PatientService;


import java.io.IOException;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService, DepartmentService departmentService, DoctorService doctorService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.departmentService = departmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }
    @GetMapping("/{id}")
    public String getAllAppointment(@PathVariable Long id, Model model, @ModelAttribute("department")Department department,
                                    @ModelAttribute("doctor")Doctor doctor, @ModelAttribute("patient")Patient patient){
        model.addAttribute("appointments",appointmentService.getAllAppointments(id));
        model.addAttribute("departments",departmentService.getAllDepartmentList(id));
        model.addAttribute("doctors",doctorService.getAllDoctorsList(id));
        model.addAttribute("patients",patientService.getAllPatientList(id));
        model.addAttribute("hospital",id);

        return "/appointment/appointments";
    }

    @GetMapping("/{id}/addAppointment")
    public String addAppointment(Model model,@PathVariable Long id){
        model.addAttribute("appointment",new Appointment());
        model.addAttribute("hospital",id);
        return "/appointment/addAppointment" ;
    }
    @PostMapping("/{id}/saveAppointment")
    public String savePatient(@ModelAttribute("appointment") @Valid Appointment appointment,
                              @PathVariable Long id) {

        appointmentService.addAppointment(appointment, id);
        return "redirect:/appointments/"+id;
    }

    @GetMapping("/updateAppointment/{hospitalId}/{id}")
    public String updateAppointment(@PathVariable("id") Long id,@PathVariable("hospitalId") Long hospitalId, Model model) {
        Appointment  appointment = appointmentService.getAppointmentById(id);
        System.out.println("11");
        model.addAttribute("appointment",appointment);
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("id",id);
        return "/appointment/updateAppointment";
    }

    @PostMapping("/{hospitalId}/{id}/saveUpdateAppointment")
    public String saveUpdateAppointment(@PathVariable("hospitalId") Long hospitalId,
                                        @PathVariable("id") Long id,
                                        @ModelAttribute("appointment")  Appointment appointment) {

        appointmentService.updateAppointment(id,appointment);
        return "redirect:/appointments/"+hospitalId;
    }

    @GetMapping ("/{hospitalId}/{id}/deleteAppointment")
    public String deleteAppointment(@PathVariable("id") Long id, @PathVariable("hospitalId") Long hospitalId) {
        appointmentService.deleteAppointment(id,hospitalId);
        return "redirect:/appointments/"+hospitalId;
    }

    @PostMapping("/{hospitalId}/{appointmentId}/assignDepartmentToAppointment")
    private String assignDepartmentToAppointment(
            @PathVariable("appointmentId") Long appointmentId,
            @ModelAttribute("department") Department department,
            @PathVariable("hospitalId") Long hospitalId)
            throws IOException {

        departmentService.assignDepartmentToAppointment(appointmentId,department.getId());
        return "redirect:/appointments/"+hospitalId;
    }
    @PostMapping("/{hospitalId}/{appointmentId}/assignDoctor")
    private String assignDoctor(
            @PathVariable("appointmentId") Long appointmentId,
            @ModelAttribute("doctor") Doctor doctor,
            @PathVariable("hospitalId") Long hospitalId)
            throws IOException {

        doctorService.assignDoctor(doctor.getId(),appointmentId);
        return "redirect:/appointments/"+hospitalId;
    }

    @PostMapping("/{hospitalId}/{appointmentId}/assignPatient")
    private String assignPatient(
            @PathVariable("appointmentId") Long appointmentId,
            @ModelAttribute("patient") Patient patient,
            @PathVariable("hospitalId") Long hospitalId)
            throws IOException {

        patientService.assignPatient(patient.getId(),appointmentId);

        return "redirect:/appointments/"+hospitalId;
    }
}
