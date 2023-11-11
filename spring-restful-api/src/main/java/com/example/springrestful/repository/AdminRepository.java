package com.example.springrestful.repository;


import com.example.springrestful.model.entity.Admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findById (int id);
    List<Admin> findAll();
    List<Admin> findByRole(String role);
    List<Admin> findByStatus(int status);
    List<Admin> findByManagedBy(Integer managedBy);
//    @Query(value = "SELECT a FROM Admin a WHERE a.accountAdmin.status = :status")
//    List<Admin> findByAccountStatusWithJPQL(@Param("status") int status);
    @Query(value = "SELECT a FROM Admin a WHERE a.accountAdmin.id = :id")
    List<Admin> findByAccountWithJPQL(@Param("id") int id);

}
