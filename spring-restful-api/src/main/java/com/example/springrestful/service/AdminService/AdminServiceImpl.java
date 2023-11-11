package com.example.springrestful.service.AdminService;


import com.example.springrestful.model.entity.Account.Account;
import com.example.springrestful.model.entity.Admin.Admin;
import com.example.springrestful.model.entity.Admin.AdminProfile;
import com.example.springrestful.model.mapper.AdminMapper;
import com.example.springrestful.model.mapper.AdminProfileMapper;
import com.example.springrestful.model.request.RequestAdmin.RequestAdmin;
import com.example.springrestful.model.request.RequestAdmin.RequestAdminProfile;
import com.example.springrestful.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springrestful.model.response.ResponseAdmin.ResponseAdminProfile;
import com.example.springrestful.repository.AccountRepository;
import com.example.springrestful.repository.AdminProfileResponsitory;
import com.example.springrestful.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AdminProfileResponsitory adminProfileResponsitory;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminProfileMapper adminProfileMapper;
    @Override
    public int countNumberAccessWebsite() throws Exception {
        return 0;
    }

    @Override
    public int countNumberUserRegister() throws Exception {
        return 0;
    }

    @Override
    public int countNumberUserLogin() throws Exception {
        return 0;
    }

    @Override
    public int countNumberTransactionsSuccessful() throws Exception {
        return 0;
    }

    @Override
    public int countMostPopularJob() throws Exception {
        return 0;
    }

    @Override
    public int countMostPopularRecruitment() throws Exception {
        return 0;
    }

    @Override
    public int countMostPopularSkill() throws Exception {
        return 0;
    }

    @Override
    public int countMostPopularCategory() throws Exception {
        return 0;
    }
//==========================Admin==========================
    @Override
    public Admin findById(int id) throws Exception {
        return adminRepository.findById(id);
    }

    @Override
    public List<Admin> findAll() throws Exception {
        return adminRepository.findAll();
    }

    @Override
    public List<Admin> findByRole(String role) throws Exception {
//        return adminMapper.toResponseAdminList(adminRepository.findByRole(role));
        return adminRepository.findByRole(role);
    }

    @Override
    public List<ResponseAdmin> findByStatus(int status) throws Exception {
        return adminMapper.toResponseAdminList(adminRepository.findByStatus(status));
    }

    @Override
    public List<ResponseAdmin> findByManagedBy(Integer managedBy) throws Exception {
        return adminMapper.toResponseAdminList(adminRepository.findByManagedBy(managedBy));
    }

//    @Override
//    public List<ResponseAdmin> findByAccountStatus(int accountStatus) {
//        return adminMapper.toResponseAdminList(adminRepository.findByAccountStatusWithJPQL(accountStatus));
//    }

    @Override
    public List<Admin> findByAccount(int accountId)throws Exception {
//        return adminMapper.toResponseAdminList(adminRepository.findByAccountWithJPQL(accountId));
        return adminRepository.findByAccountWithJPQL(accountId);
    }

    @Override
    public ResponseAdmin save(RequestAdmin admin) {
        Account account = accountRepository.findById(admin.getAccountId());
        if (account!=null){
            Admin saveAdmin = new Admin();
            saveAdmin.setId(admin.getId());
            saveAdmin.setDescription(admin.getDescription());
            saveAdmin.setRole(admin.getRole());
            saveAdmin.setMajor(admin.getMajor());
            saveAdmin.setStatus(admin.getStatus());
            saveAdmin.setExperienceYear(admin.getExperienceYear());
            saveAdmin.setAccountAdmin(account);


            return adminMapper.toResponseAdmin(adminRepository.save(saveAdmin));
        }
        return null;
    }

    @Override
    public void removeById(int id) throws Exception {

        adminRepository.deleteById(id);
    }



    //==========================AdminProfile==========================
    @Override
    public List<AdminProfile> findAllProfile() throws Exception {
        return adminProfileResponsitory.findAll();
    }
    @Override
    public ResponseAdminProfile saveProfile(RequestAdminProfile adminProfile)throws Exception {
        Admin admin=adminRepository.findById(adminProfile.getAdmin_id());
        if (admin!=null){
            AdminProfile saveAdminProfile = new AdminProfile();
            saveAdminProfile.setId(adminProfile.getId());
            saveAdminProfile.setDescription(adminProfile.getDescription());
            saveAdminProfile.setTitle(adminProfile.getTitle());
            saveAdminProfile.setLink(adminProfile.getLink());
            saveAdminProfile.setDateCreated(new Date());
            saveAdminProfile.setDateUpdated(new Date());
            saveAdminProfile.setAdminAdminProfile(admin);

            return adminProfileMapper.toResponseAdminProfile(adminProfileResponsitory.save(saveAdminProfile));
        }
        return null;


    }
//    @Override
//    public AdminProfile save(AdminProfile adminProfile)throws Exception {
//        return adminProfileResponsitory.saveAndFlush(adminProfile);
//    }

    @Override
    public ResponseAdminProfile findProfileById(int id) throws Exception {
        return adminProfileMapper.toResponseAdminProfile(adminProfileResponsitory.findById(id));
    }
    @Override
    public void removeProfileById(int id) throws Exception {
        adminProfileResponsitory.deleteById(id);
    }
}
