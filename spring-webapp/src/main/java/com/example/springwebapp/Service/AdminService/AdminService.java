package com.example.springwebapp.Service.AdminService;

import com.example.springwebapp.model.request.RequestAdmin.RequestAdmin;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseAdmin.ResponseAdmin;

import java.util.List;

public interface AdminService {
//    int countNumberAccessWebsite () throws Exception;
//    int countNumberUserRegister () throws Exception;
//    int countNumberUserLogin () throws Exception;
//    int countNumberTransactionsSuccessful () throws Exception;
//    int countMostPopularJob () throws Exception;
//    int countMostPopularRecruitment () throws Exception;
//    int countMostPopularSkill () throws Exception;
//    int countMostPopularCategory () throws Exception;

    //===========================Admin=============================
    ResponseAdmin findById(int id);
    List<ResponseAdmin> findAll();
    List<ResponseAdmin> findByRole(String role);
    List<ResponseAdmin> findByStatus(int status);

    ApiResponse<ResponseAdmin> update(RequestAdmin admin);

    //    List<ResponseAdmin> findByManagedBy(Integer managedBy)throws Exception;
//    List<ResponseAdmin> findByAccountStatus(int accountStatus);
    List<ResponseAdmin> findByAccount(int accountId);
    ApiResponse<ResponseAdmin> save(RequestAdmin admin);
//    void removeById (int id) throws Exception;
    //===========================AdminProfile=============================
//    ResponseAdminProfile findProfileById(int id)throws Exception;
//    List<ResponseAdminProfile> findAllProfile()throws Exception;
//
//    ResponseAdminProfile saveProfile(RequestAdminProfile admin)throws Exception;
//    void removeProfileById(int id) throws Exception;
}
