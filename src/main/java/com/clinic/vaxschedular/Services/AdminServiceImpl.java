package com.clinic.vaxschedular.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.clinic.vaxschedular.DTO.*;
import com.clinic.vaxschedular.Entity.*;
import com.clinic.vaxschedular.Repository.*;
import com.clinic.vaxschedular.Response.DuplicateEntryException;
import com.clinic.vaxschedular.Response.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Autowired
    private VaccineCenter_Vaccine_Repo vaccineCenter_Vaccine_Repo;

    @Autowired
    private CertificationRepo certificationRepo;
    @Autowired
    private Patient_Vaccine_Repo patient_Vaccine_Repo;

    @Override
    public String removePatient(int id) throws NotFoundException {
        Patient existPatient = patientRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient Not Found."));
        patientRepo.delete(existPatient);
        return "Patient Removed Successfully!";
    }

    @Override
    public String addVaccinationCenter(VaccinationCenter vaccinationCenter) {

        vaccineCenterRepo.findByCenterName(vaccinationCenter.getCenterName()).ifPresent(existCenter -> {
            throw new DuplicateEntryException(
                    "Center with Name " + vaccinationCenter.getCenterName() + " already exists.");
        });
        vaccineCenterRepo.findByEmail(vaccinationCenter.getEmail()).ifPresent(existCenter -> {
            throw new DuplicateEntryException(
                    "Center with Email " + vaccinationCenter.getEmail() + " already exists.");
        });
        vaccinationCenter.setPassword(passwordEncoder.encode(vaccinationCenter.getPassword()));
        vaccineCenterRepo.save(vaccinationCenter);
        Role role = new Role("CENTER", vaccinationCenter.getEmail(), vaccinationCenter.getPassword());
        roleRepo.save(role);
        return "Center Added Successfully!";
    }

    @Override
    public String addAdmin(Admin admin) throws DuplicateEntryException {
        Optional<Admin> existAdmin = adminRepo.findByEmail(admin.getEmail());

        adminRepo.findBySsn(admin.getSsn()).ifPresent(existingAdmin -> {
            throw new DuplicateEntryException("Admin with SSN " + admin.getSsn() + " already exists.");
        });

        if (existAdmin.isPresent()) {
            throw new DuplicateEntryException("Admin with " + admin.getEmail() + " already exists.");
        } else {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminRepo.save(admin);
            Role role = new Role("ADMIN", admin.getEmail(), admin.getPassword());
            roleRepo.save(role);
            return "Admin Added Successfully!";
        }
    }

    @Override
    public List<PatientDTO> listPatients() {
        List<Patient> patients = patientRepo.findAll();
        List<PatientDTO> patientdDtos = new ArrayList<>();

        for (Patient patient : patients) {
            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setPatient_Id(patient.getId());
            patientDTO.setPatient_Email(patient.getEmail());
            patientDTO.setPassword(patient.getPassword());
            patientDTO.setVaccination_Center(patient.getVaccinationCenter().getCenterName());
            patientdDtos.add(patientDTO);
        }
        return patientdDtos;
    }

    // @Override
    // public List<Vaccine> listVaccine() {
    // List<Vaccine> vaccines = vaccineRepo.findAll();
    // List<VaccineCenter_Vaccine> vaccinationCenters =
    // vaccineCenter_Vaccine_Repo.findAll();

    // for (Vaccine vaccine : vaccines) {
    // List<VaccinationCenter> vaccineCenterDTOs = new ArrayList<>();
    // for (VaccineCenter_Vaccine vaccinationCenter : vaccinationCenters) {
    // if (vaccinationCenter.getVaccineId() == vaccine.getId()) {
    // vaccineCenterDTOs.add(vaccineCenterRepo.findById(vaccinationCenter.getId()).get());
    // }
    // }
    // vaccine.setVaccinationCenters(vaccineCenterDTOs);
    // }

    // return vaccines;
    // }

    @Override
    public List<VaccinationCenter> listVaccinationCenter(int id) {
        List<VaccinationCenter> vaccinationCenters = vaccineCenterRepo.findAll();
        List<VaccinationCenter> ExistvaccinationCenters = new ArrayList<>();
        for (VaccinationCenter vaccinationCenter : vaccinationCenters) {
            if (vaccinationCenter.getId() == id)
                ExistvaccinationCenters.add(vaccinationCenter);
        }
        return ExistvaccinationCenters;
    }

    @Override
    public String updateVaccinationCenter(int id, VaccinationCenter vaccinationCenter) {

        VaccinationCenter existCenter = vaccineCenterRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Center With id : " + id + " Does not Exist"));
        existCenter.setAdminId(vaccinationCenter.getAdminId());
        existCenter.setCenterName(vaccinationCenter.getCenterName());
        existCenter.setPassword(vaccinationCenter.getPassword());
        existCenter.setEmail(vaccinationCenter.getEmail());
        existCenter.setLocation(vaccinationCenter.getLocation());
        existCenter.setPhoneNum(vaccinationCenter.getPhoneNum());
        vaccineCenterRepo.save(existCenter);
        return "Vaccination Ceneterr Updated Successfully!";
    }

    public String updateVaccinationCenter(int center_id, int vaccine_id) {
        if (vaccineRepo.findById(vaccine_id).isPresent() && vaccineCenterRepo.findById(center_id).isPresent()) {
            VaccineCenter_Vaccine vaccine = new VaccineCenter_Vaccine(center_id, vaccine_id);
            vaccineCenter_Vaccine_Repo.save(vaccine);
            return "Done";
        }
        return "Failed";
    }

    @Override
    public String deleteVaccinationCenter(int id) {
        if (vaccineCenterRepo.findById(id).isPresent()) {
            vaccineCenterRepo.deleteById(id);
            return "Vaccination Center Deleted Successfully";
        } else {
            throw new NotFoundException("Center With id : " + id + " Does not Exist");
        }
    }

    @Override
    public String createVaccine(Vaccine vaccine) {

        vaccineRepo.findByVaccineName(vaccine.getVaccineName()).ifPresent(existVaccine -> {
            throw new DuplicateEntryException(
                    "Vaccine With Name " + vaccine.getVaccineName() + " Already Exist");
        });
        vaccineRepo.save(vaccine);
        return "Vaccine Added Successfully";
    }

    @Override
    public List<Vaccine> listVaccine() {
        List<Vaccine> vaccines = vaccineRepo.findAll();
        List<VaccineCenter_Vaccine> vaccinationCenters = vaccineCenter_Vaccine_Repo.findAll();

        for (Vaccine vaccine : vaccines) {
            List<VaccinationCenter> vaccineCenterDTOs = new ArrayList<>();
            for (VaccineCenter_Vaccine vaccinationCenter : vaccinationCenters) {
                if (vaccinationCenter.getVaccineId() == vaccine.getId()) {
                    vaccineCenterDTOs.add(vaccineCenterRepo.findById(vaccinationCenter.getId()).get());
                }
            }
            vaccine.setVaccinationCenters(vaccineCenterDTOs);
        }

        return vaccines;
    }

    @Override
    public String deleteVaccine(int id) {
        if (vaccineRepo.findById(id).isPresent()) {
            vaccineRepo.deleteById(id);
            return "Deleted Successfully";
        } else {
            throw new NotFoundException("Vaccine With id : " + id + " Does not Exist");
        }
    }

    @Override
    public String updateVaccine(int id, Vaccine vaccine) {

        Vaccine existVaccine = vaccineRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Vaccine With id : " + id + " Does not Exist"));
        existVaccine.setVaccineName(vaccine.getVaccineName());
        existVaccine.setDurationBetweenDoses(vaccine.getDurationBetweenDoses());
        existVaccine.setPrecautions(vaccine.getPrecautions());
        vaccineRepo.save(existVaccine);
        return "Updaed Successfully";

    }

    @Override
    public String uploadCertificate(Certification certificate) {

        if (patientRepo.findById(certificate.getPatientId()).isPresent()) {

            Optional<Patient_Vaccine> exist = patient_Vaccine_Repo.findByVaccidAndPatid(certificate.getVaccineId(),
                    certificate.getPatientId());
            if (exist.get().isSecondDose()) {

                if (patientRepo.findById(certificate.getId()).get().getVaccineCenter() == certificate
                        .getVaccinationCenterId()) {
                    certificationRepo.save(certificate);
                    return "uploaded Successfully";
                } else {
                    return "Wrong Center Name";
                }
            } else {
                return "Second Dose Required";
            }
        }
        return "Patioent Not Found";
    }
}
