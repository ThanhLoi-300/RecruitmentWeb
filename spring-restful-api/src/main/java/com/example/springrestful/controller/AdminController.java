package com.example.springrestful.controller;

import com.example.springrestful.exception.ApplicationException;
import com.example.springrestful.exception.NotFoundException;
import com.example.springrestful.exception.ValidationException;
import com.example.springrestful.model.entity.Role.Role;
import com.example.springrestful.model.mapper.AdminMapper;
import com.example.springrestful.model.request.RequestAccount.RequestAccountEdit;
import com.example.springrestful.model.request.RequestAccount.RequestAccountRegister;
import com.example.springrestful.model.request.RequestAdmin.RequestAdmin;
import com.example.springrestful.model.response.ApiResponse.ApiResponse;
import com.example.springrestful.model.response.ApiResponse.StatusEnum;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;
import com.example.springrestful.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springrestful.service.AccountService.AccountService;
import com.example.springrestful.service.AdminService.AdminService;
import com.example.springrestful.util.ValidatorUtil;
import com.example.springrestful.validator.AccountValidator.AccountEditValidator;
import com.example.springrestful.validator.AccountValidator.AccountRegisterValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @Autowired
    ValidatorUtil validatorUtil;


//    @GetMapping(value = {"","/list"})
//    public ResponseEntity<List<ResponseAdmin>> getAllAdmin(@RequestParam(value = "role", required = false) String role) throws Exception {
//        List<Admin> adminList = null;
//        if (role == null) {
//            adminList= adminService.findAll();
//        } else {
//            adminList = adminService.findByRole(role);
//        }
//        return new  ResponseEntity<>(adminMapper.toResponseAdminList(adminList), HttpStatus.OK);
//    }


    @GetMapping("/form/{id}")
    public ResponseEntity<ResponseAdmin> returnAdminById(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(adminMapper.toResponseAdmin(adminService.findById(id)), HttpStatus.OK);
    }
    @GetMapping(value = "/form/id")
    public ResponseEntity<ResponseAdmin> getByIdByRequestParam(@RequestParam int id) throws Exception {
        return new ResponseEntity<>(adminMapper.toResponseAdmin(adminService.findById(id)), HttpStatus.OK);
    }
    //Get loại trả về role=admin/candidate/recruiter

    @PostMapping("/createAdmin")
    public ResponseEntity<ApiResponse<ResponseAdmin>> addAdmin(@Valid @RequestBody RequestAccountRegister requestAccountRegister, BindingResult bindingResult) throws Exception {

        ApiResponse apiResponse = new ApiResponse();

        if (bindingResult.hasErrors()) {
            apiResponse.ok(new ResponseAdmin());
            apiResponse.setMessage(bindingResult.getFieldError());
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ResponseAccount responseAccount = accountService.saveAccount(requestAccountRegister);
        if(responseAccount == null) apiResponse.error("Tài khoản đã tồn tại");
        else apiResponse.ok(responseAccount);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/editAdmin")
    public ResponseEntity<ApiResponse<ResponseAdmin>> editAdmin(@Valid @RequestBody RequestAccountEdit requestAccountEdit,
            BindingResult bindingResult) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        System.out.println(requestAccountEdit);
        if (bindingResult.hasErrors()) {
            apiResponse.ok(new ResponseAdmin());
            apiResponse.setMessage(bindingResult.getFieldError());
            apiResponse.setStatus(StatusEnum.ERROR);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (requestAccountEdit != null) {
            ResponseAccount responseAccount = accountService.saveAccount(requestAccountEdit);
            apiResponse.ok(responseAccount);
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
//    @GetMapping(value = {"profile", "profile/list"})
//    public ResponseEntity<ApiResponse<ResponseAdminProfile>> returnAdminProfiles() throws Exception {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.ok(adminService.findAllProfile());
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }
//
//    @GetMapping("profile/{id}")
//    public ResponseEntity<ApiResponse<ResponseAdminProfile>> returnAdminProfileById(@PathVariable int id) throws Exception {
//        ApiResponse apiResponse = new ApiResponse();
//        ResponseAdminProfile adminProfile = adminService.findProfileById(id);
//        apiResponse.ok(adminProfile);
//
//        if (adminProfile != null) {
//            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//        }
//        apiResponse.setStatus(StatusEnum.ERROR);
//        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
//    }

//    @PostMapping("profile")
//    public ResponseEntity<ApiResponse<ResponseAdminProfile>> addAdminProfile(@Valid @RequestBody RequestAdminProfile requestAdminProfile, BindingResult bindingResult) throws Exception {
//
//        ApiResponse apiResponse = new ApiResponse();
//
//        if (bindingResult.hasErrors()) {
//            apiResponse.ok(new ResponseAdminProfile());
//            apiResponse.setMessage(bindingResult.getFieldError());
//            apiResponse.setStatus(StatusEnum.ERROR);
//            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        if (requestAdminProfile != null) {
//
//            ResponseAdminProfile responseAdminProfile = adminService.saveProfile(requestAdminProfile);
//            if (responseAdminProfile != null) {
//                apiResponse.ok(responseAdminProfile);
//                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//            }
//            apiResponse.ok(new ResponseAdminProfile());
//            apiResponse.setMessage("Cannot find admin_id.");
//            apiResponse.setStatus(StatusEnum.ERROR);
//            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        apiResponse.ok(new ResponseAdminProfile());
//        apiResponse.setMessage("Cannot save because data is null");
//        apiResponse.setStatus(StatusEnum.ERROR);
//        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @PutMapping("profile/{id}")
//    public ResponseEntity<ApiResponse<ResponseAdminProfile>> editAdminProfile(
//            @PathVariable int id, @Valid @RequestBody RequestAdminProfile requestAdminProfile,
//            BindingResult bindingResult) throws Exception {
//        requestAdminProfile.setId(id);
//        ApiResponse apiResponse = new ApiResponse();
//
//
//        if (requestAdminProfile != null) {
//            ResponseAdminProfile responseadminProfile = adminService.saveProfile(requestAdminProfile);
//            apiResponse.ok(responseadminProfile);
//            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//        }
//
//        apiResponse.ok(new ResponseAdminProfile());
//        apiResponse.setMessage("Cannot save because data is null");
//        apiResponse.setStatus(StatusEnum.ERROR);
//        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @DeleteMapping("profile/{id}")
//    public ResponseEntity<ApiResponse> deleteAdminProfile(@PathVariable int id) throws Exception {
//        ApiResponse apiResponse = new ApiResponse();
//        ResponseAdminProfile adminProfile = adminService.findProfileById(id);
//        if (adminProfile != null) {
//            adminService.removeProfileById(id);
//
//            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//        }
//        apiResponse.setStatus(StatusEnum.ERROR);
//        apiResponse.setMessage("Admin Profile not found to delete");
//        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
//    }
    //=========================Role===========================
@GetMapping("/role/{id}")
public ResponseEntity<ApiResponse<List<Role>>> returnRoleById(@PathVariable int id) throws Exception {
    try {
        ApiResponse apiResponse = new ApiResponse();
        Role role = adminService.findRoleById(id);
        apiResponse.ok(role);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    } catch (Exception ex) {
        throw new ApplicationException(ex.getMessage()); // Handle other exceptions
    }
}

    @GetMapping("/role")
    public ResponseEntity<ApiResponse<List<Role>>> returnRoles(@RequestParam(value = "name", required = false) String name) throws Exception {
        try {
            System.out.println(name);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(adminService.findAllRole(name));
            System.out.println(adminService.findAllRole(name));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/role")
    public ResponseEntity<ApiResponse<Role>> addRole(@Valid @RequestBody Role requestRole, BindingResult bindingResult) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();

            if (requestRole == null) {
                throw new NotFoundException("Cannot find Role to create");
            }

            Role role = adminService.findByRolename(requestRole.getName());
            if (role != null) {
                apiResponse.error("Name is exist");
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
            apiResponse.ok(adminService.createRole(requestRole));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PutMapping("/role")
    public ResponseEntity<ApiResponse<Role>> editRole(@Valid @RequestBody Role requestRole, BindingResult bindingResult) throws Exception {
        try {
            ApiResponse apiResponse = new ApiResponse();

            accountRegisterValidator.validateEdit(requestRole, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }
            System.out.println(adminService.editRole(requestRole));
            apiResponse.ok(adminService.editRole(requestRole));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<ApiResponse> deleteRole(@PathVariable int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Role role = adminService.findRoleById(id);
            if(accountRegisterValidator.validateDelete(role)){
                apiResponse.ok();
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
            else{
                apiResponse.error("This role can not be deleted because it has been used by some account");
                return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
            }
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }
}
