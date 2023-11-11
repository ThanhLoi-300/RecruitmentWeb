package com.example.springrestful.repository;

import com.example.springrestful.model.entity.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findById (int id);
    Account findByUsername (String username);
//    List<Account> findByRole(int role);
    @Query(value = "SELECT a FROM Account a WHERE a.role.id = :id")
    List<Account> findByRoleWithJPQL(@Param("id") int id);

}
