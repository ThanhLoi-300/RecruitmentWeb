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
import java.util.stream.Collectors;

import com.example.springwebapp.model.Static.Admin;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            ResponseAccount account = adminService.loginAccount(requestAccountLogin);
            if(account == null){
                model.addAttribute("mess", "login fail");
                return "/admin/login";
            }else {
                Admin.userName = account.getFullName();
                Admin.nameRole = account.getRole_id().getName();
                Admin.account_manage = account.getRole_id().getAccount_manage();
                Admin.admin_manage = account.getRole_id().getAdmin_manage();
                Admin.role_manage = account.getRole_id().getRole_manage();
                model.addAttribute("admin", account);
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
    @GetMapping(value = "/admin/role")
    public String getRoleView (Model model, @RequestParam(value = "name", required = false) String name) throws Exception {
        List<ResponseRole> apiResponse = adminService.getAllRole(name);
        model.addAttribute("name",name);
        model.addAttribute("listRole",apiResponse);
        return "/admin/role";
    }

    @GetMapping(value = "/admin/role/addRole")
    public String getAddRoleView (Model model) throws Exception {
        model.addAttribute("role",new RequestRole());
        return "/admin/addRole";
    }

    @PostMapping(value = "/admin/role/saveRole")
    public String postSaveRole (RedirectAttributes redirectAttributes,Model model, @ModelAttribute("role") RequestRole requestRole) throws Exception {
        if(requestRole.getName().isEmpty()){
            redirectAttributes.addFlashAttribute("mess","Name role is required");
            redirectAttributes.addFlashAttribute("role",requestRole);
            return "redirect:/admin/role/addRole";
        }

        ApiResponse<ResponseRole> apiResponse = adminService.addRole(requestRole);
        if(apiResponse.getStatus() == StatusEnum.ERROR){
            redirectAttributes.addFlashAttribute("mess","Name is exist");
            return "redirect:/admin/role/addRole";
        }

        redirectAttributes.addFlashAttribute("mess","success");
        return "redirect:/admin/role";
    }

    @GetMapping(value = "/admin/role/detailRole/{id}")
    public String getDetailRoleView (Model model,@PathVariable int id) throws Exception {
        ApiResponse<ResponseRole> apiResponse = adminService.getRoleById(id);
        model.addAttribute("role",apiResponse.getPayload());
        return "/admin/detailRole";
    }
    @PostMapping(value = "/admin/role/editRole")
    public String getEditRoleView (RedirectAttributes redirectAttributes,Model model, @ModelAttribute("role") RequestRole requestRole) throws Exception {
        if(requestRole.getName().isEmpty()){
            redirectAttributes.addFlashAttribute("mess","Name role is required");
            redirectAttributes.addFlashAttribute("role",requestRole);
            return "redirect:/admin/role/detailRole/"+requestRole.getId();
        }
        ApiResponse<ResponseRole> apiResponse = adminService.editRole(requestRole);
        if(apiResponse.getStatus() == StatusEnum.ERROR){
            redirectAttributes.addFlashAttribute("mess","Name is exist");
            return "redirect:/admin/role/detailRole/"+requestRole.getId();
        }
        redirectAttributes.addFlashAttribute("mess","success");
        return "redirect:/admin/role/detailRole/"+requestRole.getId();
    }
    @GetMapping(value = "/admin/role/delete/{id}")
    public String deleteRole (RedirectAttributes redirectAttributes, @PathVariable int id) throws Exception {
        ApiResponse<ResponseRole> apiResponse = adminService.deleteRoleById(id);
        if(apiResponse.getStatus() == StatusEnum.ERROR){
            redirectAttributes.addFlashAttribute("mess",apiResponse.getMessage());
        }else{
            redirectAttributes.addFlashAttribute("mess","success");
        }
        return "redirect:/admin/role";
    }

    //+++++++++++++++++++++++++Account++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++User+++++++++++++++++++++++++++++++++++++++
    @GetMapping(value = "/admin/accountUser")
    public String getAccountUserView (Model model, @RequestParam(value = "userName") String userName, @RequestParam(value = "page") int page) throws Exception {
        List<ResponseAccount> apiResponse = adminService.getAllUser(userName, page);

        int pageSize = 3;
        int startIndex = (page - 1) * pageSize;
        int totalPage = (int) Math.ceil((double) apiResponse.size() / pageSize);

        apiResponse = apiResponse.stream().skip(startIndex).limit(pageSize).collect(Collectors.toList());

        model.addAttribute("userName",userName);
        model.addAttribute("listUser",apiResponse);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("page",page);
        return "/admin/accountUser";
    }


    @GetMapping(value = "/admin/account/profile")
    public String getProfileView () {
        return "/admin/profile";
    }

}
