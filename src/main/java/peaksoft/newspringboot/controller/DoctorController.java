package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;

import java.io.IOException;

@Controller
@RequestMapping("/{id}/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final DepartmentService departmentService;
    @Autowired
    public DoctorController(DoctorService doctorService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
    }
    @GetMapping("")
    public String getAllDoctors(@PathVariable Long id, Model model , @ModelAttribute("department")Department department){
        model.addAttribute("doctors",doctorService.getAllDoctors(id));
        model.addAttribute("departments",departmentService.getAllDepartmentList(id));
        model.addAttribute("hospital",id);

        return "/doctor/doctors";
    }

    @GetMapping("/addDoctor")
    public String addDoctor(Model model,@PathVariable Long id){
        model.addAttribute("doctor",new Doctor());
        model.addAttribute("hospital",id);


        return "/doctor/addDoctor";
    }
    @PostMapping("/saveDoctor")
    public String saveDoctor(@ModelAttribute("doctor") @Valid Doctor doctor, BindingResult bindingResult,
                             @PathVariable Long id) {
        if (bindingResult.hasErrors()){
            return "/doctor/addDoctor";
        }
        doctorService.addDoctors(doctor,id);
        return "redirect:/"+id+"/doctors";
    }

    @GetMapping("/{doctorId}/updateDoctor")
    public String updateDoctor(@PathVariable("doctorId") Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("id", doctor.getHospital().getId());
        return "/doctor/updateDoctors";
    }

    @PostMapping("/{doctorId}/saveUpdateDoctor")
    public String saveUpdateDoctor(@PathVariable("id") Long hospitalId,
                                   @PathVariable("doctorId") Long id,
                                   @ModelAttribute("doctor") @Valid Doctor doctor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/doctor/updateDoctors";
        }
        doctorService.updateDoctor(id,doctor);
        return "redirect:/"+hospitalId+"/doctors";
    }

    @GetMapping("/{doctorId}/deleteDoctor")
    public String deleteDoctor(@PathVariable("doctorId") Long id, @PathVariable("id") Long hospitalId) {
        doctorService.deleteDoctor(id);
        return "redirect:/"+hospitalId+"/doctors";
    }

    @PostMapping("{doctorId}/assignDepartment")
    private String assignDepartment(
            @PathVariable("doctorId") Long doctorId,
            @ModelAttribute("department") Department department)
            throws IOException {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        System.out.println(department);
        departmentService.assignDepartment(doctorId,department.getId());
        return "redirect:/doctors/"+doctor.getHospital().getId();
    }
}
