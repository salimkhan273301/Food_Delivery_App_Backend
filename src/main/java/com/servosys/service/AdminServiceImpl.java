package com.servosys.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.servosys.exception.AdminNotFoundException;
import com.servosys.model.Admin;
import com.servosys.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(Long id) throws AdminNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            return optionalAdmin.get();
        } else {
            throw new AdminNotFoundException("Admin not found with ID: " + id);
        }
    }

    @Override
    public Admin getAdminByEmail(String email) throws AdminNotFoundException {
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found"));
    }
    
    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) throws AdminNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            admin.setAdminId(id);
            return adminRepository.save(admin);
        } else {
            throw new AdminNotFoundException("Admin not found with ID: " + id);
        }
    }

    @Override
    public void deleteAdmin(Long id) throws AdminNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            adminRepository.delete(optionalAdmin.get());
        } else {
            throw new AdminNotFoundException("Admin not found with ID: " + id);
        }
    }
}

