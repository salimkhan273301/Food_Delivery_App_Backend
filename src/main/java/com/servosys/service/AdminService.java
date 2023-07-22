package com.servosys.service;

import com.servosys.exception.AdminNotFoundException;
import com.servosys.model.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmins();
    Admin getAdminById(Long id) throws AdminNotFoundException;
    Admin createAdmin(Admin admin);
    Admin updateAdmin(Long id, Admin admin) throws AdminNotFoundException;
    void deleteAdmin(Long id) throws AdminNotFoundException;
    Admin getAdminByEmail(String email) throws AdminNotFoundException;
}
