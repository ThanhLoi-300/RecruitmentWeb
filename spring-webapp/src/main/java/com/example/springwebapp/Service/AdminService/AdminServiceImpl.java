package com.example.springwebapp.Service.AdminService;



import com.example.springwebapp.model.request.RequestAccount.RequestAccountLogin;
import com.example.springwebapp.model.request.RequestAdmin.RequestAdmin;
import com.example.springwebapp.model.request.RequestRole.RequestRole;
import com.example.springwebapp.model.response.ApiResponse.ApiResponse;
import com.example.springwebapp.model.response.ResponseAccount.ResponseAccount;
import com.example.springwebapp.model.response.ResponseAdmin.ResponseAdmin;
import com.example.springwebapp.model.response.ResponseRole.ResponseRole;
import com.example.springwebapp.restapi.CommonRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private CommonRestClient commonRestClient;
    private String apiUrl= "http://localhost:8080/api/admin";
    private String apiUrlLogin= "http://localhost:8080/api";


    private final RestTemplate restTemplate = new RestTemplate();


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
    public ResponseAccount loginAccount(RequestAccountLogin requestAccountLogin) throws Exception {
        try {
            //ApiResponse response = restTemplate.getForObject(apiUrlLogin+"/account/login", ApiResponse.class);
            return commonRestClient.post(apiUrlLogin+"/account/login",ResponseAccount.class, requestAccountLogin);
        } catch (Exception e) {
            e.printStackTrace();
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
    public List<ResponseRole> getAllRole(String name) throws Exception {
        return commonRestClient.getAll(apiUrl+"/role?name="+name);
    }
    @Override
    public ApiResponse<ResponseRole> getRoleById(int id) throws Exception{
        return commonRestClient.getByConditionUnique(apiUrl+"/role",id);
    }
    @Override
    public ApiResponse<ResponseRole> deleteRoleById(int id) throws Exception{
        return commonRestClient.deleteByCondition(apiUrl+"/role",id);
    }

    @Override
    public ApiResponse<ResponseRole> editRole(RequestRole requestRole) throws Exception {
        return commonRestClient.put(apiUrl + "/role",requestRole,requestRole.getId());
    }

    @Override
    public ApiResponse<ResponseRole> addRole(RequestRole requestRole) throws Exception {
        return commonRestClient.post(apiUrl + "/role",requestRole);
    }

    @Override
    public List<ResponseAccount> getAllUser(String userName, int page) throws Exception {
        return commonRestClient.getAll(apiUrl+"/accountUser?userName="+userName+"&page="+page);
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
