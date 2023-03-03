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

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public String getAllDepartment(@PathVariable Long id, Model model){
        model.addAttribute("departments",departmentService.getAllDepartment(id));
        model.addAttribute("hospital",id);
        return "/department/departments";
    }

    @GetMapping("/{id}/addDepartment")
    public String addDepartment(Model model,@PathVariable Long id){
        model.addAttribute("department",new Department());
        model.addAttribute("hospital",id);
        return "/department/addDepartment" ;
    }
    @PostMapping("/{id}/saveDepartment")
    public String saveDepartment(@ModelAttribute("department") @Valid Department department, BindingResult bindingResult,
                             @PathVariable Long id) {
        if (bindingResult.hasErrors()){
            return "/department/addDepartment";

        }
        departmentService.addDepartment(department,id);
        return "redirect:/departments/"+id;
    }

    @GetMapping("/updateDepartment/{id}")
    public String updateDepartment(@PathVariable("id") Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        model.addAttribute("hospitalId", department.getHospital().getId());
        return "/department/updateDepartment";
    }

    @PostMapping("/{hospitalId}/{id}/saveUpdateDepartment")
    public String saveUpdateDepartment(@PathVariable("hospitalId") Long hospitalId,
                                   @PathVariable("id") Long id,
                                   @ModelAttribute("department") Department department) {
        departmentService.updateDepartment(id,department);
        return "redirect:/departments/"+hospitalId;
    }

    @GetMapping ("/{hospitalId}/{id}/deleteDepartment")
    public String deleteDepartment(@PathVariable("id") Long id, @PathVariable("hospitalId") Long hospitalId) {
            departmentService.deleteDepartment(id);
        return "redirect:/departments/"+hospitalId;
    }


}
