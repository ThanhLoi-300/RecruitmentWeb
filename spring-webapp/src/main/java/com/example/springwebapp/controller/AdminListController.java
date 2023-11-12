package com.example.springwebapp.controller;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/6/2023
 * Time: 7:11 PM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springwebapp.model.request.RequestAccount.RequestAccountLogin;
import com.example.springwebapp.model.request.RequestRole.RequestRole;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ApiResponse.StatusEnum;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccount;
import com.example.springwebapp.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springwebapp.Service.AccountService.AccountService;
import com.example.springwebapp.Service.AdminService.AdminService;
import com.example.springwebapp.model.response.ResponseRole.ResponseRole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import com.example.springwebapp.model.Static.Admin;
@Controller
public class AdminListController {
    @Autowired
    AdminService adminService;

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/admin/list")
    public String findAll(Model model, @RequestParam(value = "role", required = false) String role) {
        List<ResponseAccount> accountList = accountService.findAllAccounts();
        List<ResponseAdmin> adminList = new ArrayList<>();

        if (role == null) {
            adminList = adminService.findAll();
        } else {
            adminList = adminService.findByRole(role);
        }

        model.addAttribute("accountList", accountList);
        model.addAttribute("adminList", adminList);
        return "/admin/admin_list";
    }

    @GetMapping(value = "/admin/role/list")
    public String allRole(Model model, @RequestParam(value = "name", required = false) String name) {

        return "role_list";
    }

    @GetMapping(value = "/admin/login")
    public String getLoginView () {
        return "/admin/login";
    }
    @PostMapping(value = "/admin/login")
    public String checkLoginView (@ModelAttribute("requestAccountLogin") @Valid RequestAccountLogin requestAccountLogin, BindingResult bindingResult, Model model) throws Exception{
        if (bindingResult.hasErrors()) {
            model.addAttribute("mess", "login fail");
            return "/admin/login";
        }else{
            requestAccountLogin.setIsAdmin(1);
            ApiResponse<ResponseAccount> apiResponse = adminService.loginAccount(requestAccountLogin);
            System.out.println("rs "+apiResponse.getPayload());
            if(apiResponse.getPayload() == null){
                model.addAttribute("mess", "login fail");
                return "/admin/login";
            }else {
//                Admin.userName = apiResponse.getPayload().getFullName();
//                Admin.nameRole = apiResponse.getPayload().getRole_id().getName();
//                Admin.account_manage = apiResponse.getPayload().getRole_id().getAccount_manage();
//                Admin.admin_manage = apiResponse.getPayload().getRole_id().getAdmin_manage();
//                Admin.role_manage = apiResponse.getPayload().getRole_id().getRole_manage();
                //model.addAttribute("admin", apiResponse.getPayload());
                return "redirect:/admin/index";
            }
        }
    }

    @GetMapping(value = "/admin/index")
    public String getIndexView () {
        return "/admin/index";
    }
    @GetMapping(value = "/admin/account")
    public String getAccountView () {

        return "/admin/account";
    }
    @GetMapping(value = "/admin/account/profile")
    public String getProfileView () {
        return "/admin/profile";
    }
    @GetMapping(value = "/admin/role")
    public String getRoleView (Model model) throws Exception {
        List<ResponseRole> apiResponse = adminService.getAllRole();
        model.addAttribute("listRole",apiResponse);
        return "/admin/role";
    }
    @GetMapping(value = "/admin/role/detailRole/{id}")
    public String getDetailRoleView (Model model,@PathVariable int id) throws Exception {
        ApiResponse<ResponseRole> apiResponse = adminService.getRoleById(id);
        model.addAttribute("role",apiResponse.getPayload());
        System.out.println(apiResponse.getPayload());
        return "/admin/detailRole";
    }
    @PostMapping(value = "/admin/role/editRole")
    public String getEditRoleView (Model model, @RequestBody RequestRole requestRole) {
        if(requestRole.getName().isEmpty()){
            model.addAttribute("mess","Name role is required");
            return "/admin/detailRole";
        }
        return "/admin/detailRole";
    }
    @GetMapping(value = "/admin/role/delete/{id}")
    public String deleteRole (Model model, @PathVariable int id) throws Exception {
        ApiResponse<ResponseRole> apiResponse = adminService.deleteRoleById(id);
        if(apiResponse.getStatus() == StatusEnum.ERROR){
            model.addAttribute("mess",apiResponse.getStatus());
        }else{
            model.addAttribute("mess",apiResponse.getMessage());
        }
        return "/admin/role";
    }

}
