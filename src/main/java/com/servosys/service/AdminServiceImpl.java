package com.servosys.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.servosys.exception.AdminNotFoundException;
import com.servosys.model.Admin;
import com.servosys.model.User;
import com.servosys.repository.AdminRepository;
import com.servosys.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(AdminRepository adminRepository,UserRepository userRepository) {
        this.adminRepository = adminRepository;
		this.userRepository = userRepository;
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
    public Admin updateAdmin(Long adminId, Admin admin) {
        Admin existingAdmin = adminRepository.findById(adminId).orElse(null);
        System.out.println("existing Admin: " + existingAdmin);
        
        if (existingAdmin != null) {
            // Keep adminId unchanged
            admin.setAdminId(existingAdmin.getAdminId());
            
            User existingUser = existingAdmin.getUser();
            System.out.println("existing User: " + existingUser);
            
            if (existingUser != null) {
                existingUser.setEmail(admin.getEmail());
                existingUser.setPassword(admin.getPassword());
                // Update other fields in User entity as needed...
            }
            
            // Update other fields
            existingAdmin.setName(admin.getName());
            existingAdmin.setEmail(admin.getEmail());
            existingAdmin.setPassword(admin.getPassword());
            existingAdmin.setAddress(admin.getAddress());
            existingAdmin.setMobileNumber(admin.getMobileNumber());
            
            return adminRepository.save(existingAdmin);
        }
        
        return null;
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

