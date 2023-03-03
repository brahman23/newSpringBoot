package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Hospital;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.HospitalService;

import java.util.List;
@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;


    @Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<Hospital> getAllHospitals() {
        try {
            return hospitalRepository.getAllHospitals();

        }catch (RuntimeException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void addHospital(Hospital hospital) {
        try {
            hospitalRepository.addHospital(hospital);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Hospital getHospitalById(Long hospitalId) {
        try {
               Hospital h = hospitalRepository.getHospitalById(hospitalId);
               return h;
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Hospital updateHospital(Long id,Hospital hospital) {
        try {
               return hospitalRepository.updateHospital(id,hospital);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public void deleteHospital(Long id) {
        try {
                hospitalRepository.deleteHospital(id);
            System.out.println(3);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }
}
