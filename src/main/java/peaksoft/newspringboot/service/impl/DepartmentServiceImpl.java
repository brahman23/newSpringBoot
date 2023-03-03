package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Department;
import peaksoft.model.Hospital;
import peaksoft.model.exeptiion.MyExeption;
import peaksoft.repository.DepartmentRepository;
import peaksoft.service.DepartmentService;

import java.io.IOException;
import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartment(Long id) {
        try {
            return departmentRepository.getAllDepartment(id);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public List<Department> getAllDepartmentList(Long id) {
        try {
            return departmentRepository.getAllDepartmentList(id);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addDepartment(Department department, Long id) {
        try {


            departmentRepository.addDepartment(department,id);

        }catch (Exception e){
            throw new MyExeption("addDepartment");
        }

    }

    @Override
    public Department getDepartmentById(Long Id) {
        return departmentRepository.getDepartmentById(Id);
    }

    @Override
    public void updateDepartment(Long Id, Department department) {
        System.out.println(2);
        departmentRepository.updateDepartment(Id, department);
    }

    @Override
    public void deleteDepartment(Long id) {
        try {
            System.out.println(33);
            departmentRepository.deleteDepartment(id);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void assignDepartment(Long doctorId, Long departmentId) throws IOException {
        departmentRepository.assignDepartment(doctorId, departmentId);

    }

    @Override
    public void assignDepartmentToAppointment(Long appointmentId, Long departmentId) throws IOException {
        try {
            departmentRepository.assignDepartmentToAppointment(appointmentId, departmentId);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
