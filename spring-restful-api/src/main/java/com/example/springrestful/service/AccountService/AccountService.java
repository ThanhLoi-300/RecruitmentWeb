package com.example.springrestful.service.AccountService;

import com.example.springrestful.model.entity.Account.Account;
import com.example.springrestful.model.entity.Account.AccountRole;
import com.example.springrestful.model.request.RequestAccount.RequestAccountEdit;
import com.example.springrestful.model.request.RequestAccount.RequestAccountRegister;
import com.example.springrestful.model.response.ResponseAccount.ResponseAccount;

import java.util.List;

public interface AccountService {
    ResponseAccount saveAccount (RequestAccountRegister account,AccountRole role) throws Exception;
    ResponseAccount saveAccount (RequestAccountEdit account,AccountRole role) throws Exception;
    void removeAccountById (int id) throws Exception;
    List<ResponseAccount> findAllAccounts () throws Exception;
    ResponseAccount findAccountById (int id) throws Exception;
    ResponseAccount findByUsername (String username) throws Exception;
    List<Account> findAll()throws Exception;
    List<Account> findByRole(int role)throws Exception;
    Account findById(int id)throws Exception;

}
