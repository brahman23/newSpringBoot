package peaksoft.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Hospital;
import peaksoft.service.HospitalService;



@Controller
@RequestMapping("/getAllHospital")
public class HospitalController {

    private final HospitalService hospitalS;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalS = hospitalService;
    }
    @GetMapping
    public String getAllHospital(Model model){
        model.addAttribute("hospitals",hospitalS.getAllHospitals());
        return "/hospital/getAllHospital";
    }
    @GetMapping("/addHospital")
    public String addHospital(Model model){
        model.addAttribute("hospital",new Hospital());
        return "/hospital/addHospital";
    }
    @PostMapping("/saveHospital")
    public String saveCompany(@ModelAttribute("hospital") @Valid Hospital hospital, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/hospital/addHospital";
        }

        hospitalS.addHospital(hospital);
        return "redirect:/getAllHospital";
    }
    @GetMapping("/{hospitalId}/updateHospital")
    public String updateHospital(@PathVariable("hospitalId") Long id, Model model) {
        Hospital hospital = hospitalS.getHospitalById(id);
        model.addAttribute("hospital", hospital);
        return "/hospital/updateHospital";
    }

    @PostMapping("/{hospitalId}/updateHospital")
    public String saveUpdateHospital(@ModelAttribute("hospital") @Valid Hospital hospital, BindingResult bindingResult, @PathVariable("hospitalId")Long id) {
        if (bindingResult.hasErrors()){
            return "/hospital/updateHospital";
        }
        hospitalS.updateHospital(id, hospital);
        return "redirect:/getAllHospital";
    }
    @GetMapping("{hospitalId}/deleteHospital")
    public String deleteHospital(@PathVariable("hospitalId") Long hospitalId) {
        hospitalS.deleteHospital(hospitalId);
        System.out.println("1");
        return "redirect:/getAllHospital";
    }

}
