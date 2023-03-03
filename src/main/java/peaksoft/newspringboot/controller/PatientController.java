package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Department;
import peaksoft.model.Patient;
import peaksoft.service.PatientService;

@Controller
@RequestMapping("/{id}/patients")
public class PatientController {

private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public String getAllPatients(@PathVariable Long id, Model model){
        model.addAttribute("patients",patientService.getAllPatient(id));
        model.addAttribute("hospital",id);
        return "/patient/patients";
    }

    @GetMapping("/addPatient")
    public String addPatient(Model model,@PathVariable Long id){
        model.addAttribute("patient",new Patient());
        model.addAttribute("hospital",id);
        return "/patient/addPatient" ;
    }
    @PostMapping("/savePatient")
    public String savePatient(@ModelAttribute("patient")  @Valid Patient patient, BindingResult bindingResult,
                                 @PathVariable Long id) {
        if (bindingResult.hasErrors()){
            return "/patient/addPatient";
        }
        patientService.addPatient(patient,id);
        return "redirect:/"+id+"/patients";
    }

    @GetMapping("/{patientId}/updatePatient")
    public String updatePatient(@PathVariable("patientId") Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient",patient);
        model.addAttribute("id", patient.getHospital().getId());
        return "/patient/updatePatient";
    }

    @PostMapping("/{patientId}/saveUpdatePatient")
    public String saveUpdatePatient(@PathVariable("id") Long hospitalId,
                                    @PathVariable("patientId") Long id,
                                    @ModelAttribute("patient") @Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/patient/updatePatient";
        }
        patientService.updatePatient(id,patient);
        return "redirect:/"+hospitalId+"/patients";
    }

    @GetMapping ("/{patientId}/deletePatient")
    public String deletePatient(@PathVariable("patientId") Long id, @PathVariable("id") Long hospitalId) {
        patientService.deletePatient(id);
        return "redirect:/"+hospitalId+"/patients";
    }
    
}
