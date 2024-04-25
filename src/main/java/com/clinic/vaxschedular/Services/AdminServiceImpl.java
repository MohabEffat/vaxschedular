package com.clinic.vaxschedular.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.clinic.vaxschedular.Entity.*;
import com.clinic.vaxschedular.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private VaccinationCenterRepo vaccineCenterRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private VaccineRepo vaccineRepo;

    @Override
    public String removePatient(Patient patient) {
        Optional<Patient> existPatient = patientRepo.findByEmail(patient.getEmail());
        if (existPatient.isPresent()) {
            patientRepo.delete(existPatient.get());
            return "Removed Successfully";
        }
        return "Patient Not Found";
    }




    @Override
    public String addVaccinationCenter(VaccinationCenter vaccinationCenter) {

        Optional<VaccinationCenter> existCenter = vaccineCenterRepo.findByEmail(vaccinationCenter.getEmail());
        if (existCenter.isPresent()) {
            throw new RuntimeException("Email " + vaccinationCenter.getEmail() + " already exist");
        } else {
            vaccinationCenter.setPassword(passwordEncoder.encode(vaccinationCenter.getPassword()));
            vaccineCenterRepo.save(vaccinationCenter);
            Role role = new Role("CENTER", vaccinationCenter.getEmail(), vaccinationCenter.getPassword());
            roleRepo.save(role);
        }
        return "Center Added Successfully!";
    }


    @Override

    public String addAdmin(Admin admin) {
        Optional<Admin> existAdmin = adminRepo.findById(admin.getId());
        if (existAdmin.isPresent()) {
            throw new RuntimeException("Admin Manages Another Center");
        } else {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminRepo.save(admin);
            Role role = new Role("ADMIN", admin.getEmail(), admin.getPassword());
            roleRepo.save(role);
            return "DONE!";
        }
    }

    @Override
    public List<VaccinationCenter> listVaccinationCenter() {
        List<VaccinationCenter> allCenters = (List<VaccinationCenter>) vaccineCenterRepo.findAll();
        List<VaccinationCenter> centersWithoutPassword = new ArrayList<>();

        for (VaccinationCenter center : allCenters) {
            VaccinationCenter centerWithoutPassword = new VaccinationCenter();
            centerWithoutPassword.setAdmin(center.getAdmin());
            centerWithoutPassword.setId(center.getId());
            centerWithoutPassword.setAdminId(center.getAdminId());
            centerWithoutPassword.setCenterName(center.getCenterName());
            centerWithoutPassword.setEmail(center.getEmail());
            centerWithoutPassword.setPatients(center.getPatients());
            centerWithoutPassword.setPhoneNum(center.getPhoneNum());
            centerWithoutPassword.setLocation(center.getLocation());
            centerWithoutPassword.setPassword("null");

            centersWithoutPassword.add(centerWithoutPassword);
        }

        return centersWithoutPassword;
    }

    @Override
    public boolean updateVaccinationCenter(VaccinationCenter vaccinationCenter)
    {
        List<VaccinationCenter> allCenters = (List<VaccinationCenter>) vaccineCenterRepo.findAll();

        for (VaccinationCenter center : allCenters) {
            if(center.getId() == vaccinationCenter.getId())
            {
                center.setAdmin(vaccinationCenter.getAdmin());
                center.setId(vaccinationCenter.getId());
                center.setAdminId(vaccinationCenter.getAdminId());
                center.setCenterName(vaccinationCenter.getCenterName());
                center.setEmail(vaccinationCenter.getEmail());
                center.setPatients(vaccinationCenter.getPatients());
                center.setPhoneNum(vaccinationCenter.getPhoneNum());
                center.setLocation(vaccinationCenter.getLocation());
                center.setPassword(vaccinationCenter.getPassword());
                vaccineCenterRepo.save(center);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean deleteVaccinationCenter(int id)
    {
        List<VaccinationCenter> allCenters = (List<VaccinationCenter>) vaccineCenterRepo.findAll();

        for (VaccinationCenter center : allCenters) {
            if(center.getId() == id)
            {
                vaccineCenterRepo.deleteById(id);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean createVaccine(Vaccine vaccine){
    List<Vaccine> allvaccines = (List<Vaccine>) vaccineRepo.findAll();

        for (Vaccine createdVaccine : allvaccines) {
        if(createdVaccine.getId() == vaccine.getId()){
                return false;
        }
        }
        vaccineRepo.save(vaccine);
        return true;
    }

    @Override
    public  List<Vaccine> listVaccine()
    {
        return vaccineRepo.findAll();
    }


    @Override
    public boolean deleteVaccine(int id )
    {
        List<Vaccine> allvaccines = (List<Vaccine>) vaccineRepo.findAll();

        for (Vaccine deleteVaccine : allvaccines) {
            if(deleteVaccine.getId() == id)
            {
                vaccineRepo.deleteById(id);
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean updateVaccine(Vaccine vaccine)
    {
        List<Vaccine> allVaccines = (List<Vaccine>) vaccineRepo.findAll();

        for (Vaccine ubdateVaccine : allVaccines) {
            if(ubdateVaccine.getId() == vaccine.getId())
            {
                ubdateVaccine.setVaccineName(vaccine.getVaccineName());
                ubdateVaccine.setVaccinationCenterName(vaccine.getVaccinationCenterName());
                ubdateVaccine.setPrecautions(vaccine.getPrecautions());
                ubdateVaccine.setDurationBetweenDoses(vaccine.getDurationBetweenDoses());
                vaccineRepo.save(ubdateVaccine);
                return true;
            }
        }
        return false;
    }
}
