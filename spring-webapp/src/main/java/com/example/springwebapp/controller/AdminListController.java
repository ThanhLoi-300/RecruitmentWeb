package com.example.springwebapp.controller;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/6/2023
 * Time: 7:11 PM
 * To change this template use File | Settings | File and Code Templates.
 */

import com.example.springwebapp.model.response.ResponseAccount.ResponseAccount;
import com.example.springwebapp.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springwebapp.Service.AccountService.AccountService;
import com.example.springwebapp.Service.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
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
        return "admin_list";
    }

}
