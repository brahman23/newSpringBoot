package peaksoft.service;

import peaksoft.model.Department;
import peaksoft.model.Doctor;

import java.io.IOException;
import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartment(Long id);
    List<Department> getAllDepartmentList(Long id);

    void addDepartment(Department department,Long id);

    Department getDepartmentById(Long Id);

    void updateDepartment(Long Id, Department department);

    void deleteDepartment(Long id);

    void assignDepartment(Long doctorId, Long departmentId) throws IOException;

    void assignDepartmentToAppointment(Long appointmentId, Long departmentId) throws IOException;
}
