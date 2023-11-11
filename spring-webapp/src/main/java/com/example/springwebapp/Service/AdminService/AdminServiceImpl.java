package com.example.springwebapp.Service.AdminService;



import com.example.springwebapp.model.request.RequestAdmin.RequestAdmin;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springwebapp.restapi.CommonRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private CommonRestClient commonRestClient;
    private String apiUrl= "http://localhost:8080/api/admin";


    @Override
    public List<ResponseAdmin> findAll() {
        try {
            Map<String, String> params = new HashMap<>();

            List<ResponseAdmin> response = commonRestClient.get(apiUrl+"/list", new ParameterizedTypeReference<>() {}, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }
    @Override
    public List<ResponseAdmin> findByRole(String role) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("role", role);

            List<ResponseAdmin> response = commonRestClient.get(apiUrl+"/list", new ParameterizedTypeReference<>() {}, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<ResponseAdmin> findByStatus(int status) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("status", String.valueOf(status));

            List<ResponseAdmin> response = commonRestClient.get(apiUrl+"/list", new ParameterizedTypeReference<>() {}, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ResponseAdmin findById(int id) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("id", String.valueOf(id));

            ResponseAdmin response = commonRestClient.get(apiUrl+"/form/id", ResponseAdmin.class, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ApiResponse<ResponseAdmin> save(RequestAdmin admin) {
        try {
            ApiResponse response = commonRestClient.post(apiUrl+"/form", ApiResponse.class, admin);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @Override
    public ApiResponse<ResponseAdmin> update(RequestAdmin admin) {
        try {
            ApiResponse response = commonRestClient.put(apiUrl+"/form", ApiResponse.class, admin);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @Override
    public List<ResponseAdmin> findByAccount(int accountId){
        try {
            Map<String, String> params = new HashMap<>();
            params.put("account", String.valueOf(accountId));

            List<ResponseAdmin> response = commonRestClient.get(apiUrl+"/list", new ParameterizedTypeReference<>() {}, params);
            return response;
        } catch (Exception ex) {
            return null;
        }
    }
}
