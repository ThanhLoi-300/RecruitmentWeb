package com.example.springrestful.controller;

import com.example.springrestful.model.entity.Admin.Admin;
import com.example.springrestful.model.mapper.AdminMapper;
import com.example.springrestful.model.request.RequestAdmin.RequestAdmin;
import com.example.springrestful.model.request.RequestAdmin.RequestAdminProfile;
import com.example.springrestful.model.response.ApiResponse.ApiResponse;
import com.example.springrestful.model.response.ApiResponse.StatusEnum;
import com.example.springrestful.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springrestful.model.response.ResponseAdmin.ResponseAdminProfile;
import com.example.springrestful.service.AccountService.AccountService;
import com.example.springrestful.service.AdminService.AdminService;
import com.example.springrestful.validator.AccountValidator.AccountEditValidator;
import com.example.springrestful.validator.AccountValidator.AccountRegisterValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    AccountRegisterValidator accountRegisterValidator;
    @Autowired
    AccountEditValidator accountEditValidator;
    @Autowired
    AccountService accountService;

    @Autowired
    AdminService adminService;


    @GetMapping(value = {"","/list"})
    public ResponseEntity<List<ResponseAdmin>> getAllAdmin(@RequestParam(value = "role", required = false) String role) throws Exception {
        List<Admin> adminList = null;
        if (role == null) {
            adminList= adminService.findAll();
        } else {
            adminList = adminService.findByRole(role);
        }
        return new  ResponseEntity<>(adminMapper.toResponseAdminList(adminList), HttpStatus.OK);
    }


    @GetMapping("/form/{id}")
    public ResponseEntity<ResponseAdmin> returnAdminById(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(adminMapper.toResponseAdmin(adminService.findById(id)), HttpStatus.OK);
    }
    @GetMapping(value = "/form/id")
    public ResponseEntity<ResponseAdmin> getByIdByRequestParam(@RequestParam int id) throws Exception {
        return new ResponseEntity<>(adminMapper.toResponseAdmin(adminService.findById(id)), HttpStatus.OK);
    }
    //Get loại trả về role=admin/candidate/recruiter

    @PostMapping("form")
    public ResponseEntity<ApiResponse<ResponseAdmin>> addAdmin(@Valid @RequestBody RequestAdmin requestAdmin, BindingResult bindingResult) throws Exception {

        ApiResponse apiResponse = new ApiResponse();

        if (bindingResult.hasErrors()) {
            apiResponse.ok(new ResponseAdmin());
            apiResponse.setMessage(bindingResult.getFieldError());
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (requestAdmin != null) {

            ResponseAdmin responseAdmin = adminService.save(requestAdmin);

            if (responseAdmin!=null){
                apiResponse.ok(responseAdmin);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
            apiResponse.ok(new ResponseAdmin());
            apiResponse.setMessage("Cannot find account_id.");
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        apiResponse.ok(new ResponseAdmin());
        apiResponse.setMessage("Cannot save because data is null");
        apiResponse.setStatus(StatusEnum.ERROR);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("form")
    public ResponseEntity<ApiResponse<ResponseAdmin>> editAdmin(@Valid @RequestBody RequestAdmin requestAdmin,
            BindingResult bindingResult) throws Exception {
        requestAdmin.setId(requestAdmin.getId());
        ApiResponse apiResponse = new ApiResponse();


        if (requestAdmin != null) {
            ResponseAdmin responseadmin = adminService.save(requestAdmin);
            apiResponse.ok(responseadmin);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        }

        apiResponse.ok(new ResponseAdmin());
        apiResponse.setMessage("Cannot save because data is null");
        apiResponse.setStatus(StatusEnum.ERROR);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @DeleteMapping("admin/{id}")
//    public ResponseEntity<ApiResponse> deleteAdmin(@PathVariable int id) throws Exception {
//        ApiResponse apiResponse = new ApiResponse();
//        ResponseAdmin admin = adminService.findById(id);
//        if (admin != null) {
////            adminService.removeById(id);
//            admin.setStatus(0);
//            RequestAdmin requestAdmin = new RequestAdmin();
//            requestAdmin.setId(Integer.parseInt(admin.getId()));
//            requestAdmin.setDescription(admin.getDescription());
//            requestAdmin.setRole(admin.getRole());
//            requestAdmin.setMajor(admin.getMajor());
//            requestAdmin.setStatus(admin.getStatus());
//            requestAdmin.setExperienceYear(admin.getExperienceYear());
//
//            adminService.save(requestAdmin);
//            apiResponse.ok(requestAdmin);
//            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//        }
//        apiResponse.setStatus(StatusEnum.ERROR);
//        apiResponse.setMessage("Admin not found to delete");
//        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
//    }

    //====================================profile=======================================================
    @GetMapping(value = {"profile", "profile/list"})
    public ResponseEntity<ApiResponse<ResponseAdminProfile>> returnAdminProfiles() throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(adminService.findAllProfile());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("profile/{id}")
    public ResponseEntity<ApiResponse<ResponseAdminProfile>> returnAdminProfileById(@PathVariable int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        ResponseAdminProfile adminProfile = adminService.findProfileById(id);
        apiResponse.ok(adminProfile);

        if (adminProfile != null) {
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        apiResponse.setStatus(StatusEnum.ERROR);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @PostMapping("profile")
    public ResponseEntity<ApiResponse<ResponseAdminProfile>> addAdminProfile(@Valid @RequestBody RequestAdminProfile requestAdminProfile, BindingResult bindingResult) throws Exception {

        ApiResponse apiResponse = new ApiResponse();

        if (bindingResult.hasErrors()) {
            apiResponse.ok(new ResponseAdminProfile());
            apiResponse.setMessage(bindingResult.getFieldError());
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (requestAdminProfile != null) {

            ResponseAdminProfile responseAdminProfile = adminService.saveProfile(requestAdminProfile);
            if (responseAdminProfile != null) {
                apiResponse.ok(responseAdminProfile);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
            apiResponse.ok(new ResponseAdminProfile());
            apiResponse.setMessage("Cannot find admin_id.");
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        apiResponse.ok(new ResponseAdminProfile());
        apiResponse.setMessage("Cannot save because data is null");
        apiResponse.setStatus(StatusEnum.ERROR);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("profile/{id}")
    public ResponseEntity<ApiResponse<ResponseAdminProfile>> editAdminProfile(
            @PathVariable int id, @Valid @RequestBody RequestAdminProfile requestAdminProfile,
            BindingResult bindingResult) throws Exception {
        requestAdminProfile.setId(id);
        ApiResponse apiResponse = new ApiResponse();


        if (requestAdminProfile != null) {
            ResponseAdminProfile responseadminProfile = adminService.saveProfile(requestAdminProfile);
            apiResponse.ok(responseadminProfile);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

        apiResponse.ok(new ResponseAdminProfile());
        apiResponse.setMessage("Cannot save because data is null");
        apiResponse.setStatus(StatusEnum.ERROR);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("profile/{id}")
    public ResponseEntity<ApiResponse> deleteAdminProfile(@PathVariable int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        ResponseAdminProfile adminProfile = adminService.findProfileById(id);
        if (adminProfile != null) {
            adminService.removeProfileById(id);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        apiResponse.setStatus(StatusEnum.ERROR);
        apiResponse.setMessage("Admin Profile not found to delete");
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
