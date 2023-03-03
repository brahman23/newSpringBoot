package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Hospital;

import java.util.List;
@Service
public interface HospitalService {
    List<Hospital> getAllHospitals();

    void addHospital(Hospital hospital);

    Hospital getHospitalById(Long hospitalId);

    Hospital updateHospital(Long id,Hospital hospital);

    void deleteHospital(Long id);
}
