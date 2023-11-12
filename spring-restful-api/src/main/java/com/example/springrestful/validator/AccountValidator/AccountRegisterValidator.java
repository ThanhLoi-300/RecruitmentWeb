package com.example.springrestful.validator.AccountValidator;

import com.example.springrestful.model.entity.Role.Role;
import com.example.springrestful.model.request.RequestAccount.RequestAccountRegister;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;
import com.example.springrestful.service.AccountService.AccountService;
import com.example.springrestful.service.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountRegisterValidator implements Validator {
    @Autowired
    AccountService accountService;
    @Autowired
    AdminService adminService;

    public void validateCreate(Object target, Errors errors) {
        try {
            Role requestRole = (Role) target;
            Role role = adminService.findByRolename(requestRole.getName());
            if (role != null) {
                errors.rejectValue("role", "error.role", "Role existed!");
            }
        } catch (Exception e) {
            try {
                throw new Exception(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void validateEdit(Object target, Errors errors) {
        try {
            Role requestRole = (Role) target;
            int rs = adminService.checkRoleExisted(requestRole.getId(),requestRole.getName());
            if (rs > 0) {
                errors.rejectValue("role", "error.role", "Role existed!");
            }
        } catch (Exception e) {
            try {
                throw new Exception(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public boolean validateDelete(Object target) {
        try {
            Role role = (Role) target;
            int rs = adminService.countAccountOfRole(role.getId());
            if (rs > 0) return false;
            else{
                adminService.removeRole(role.getId());
                return true;
            }
        } catch (Exception e) {
            try {
                throw new Exception(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            RequestAccountRegister requestAccountRegister = (RequestAccountRegister) target;
            ResponseAccount account = accountService.findByUsername(requestAccountRegister.getUsername());
            if (account != null) {
                errors.rejectValue("username", "error.username", "username existed!");
            }
        } catch (Exception e) {
            try {
                throw new Exception(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
