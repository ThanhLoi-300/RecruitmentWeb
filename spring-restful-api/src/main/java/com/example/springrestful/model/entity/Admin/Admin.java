package com.example.springrestful.model.entity.Admin;

import com.example.springrestful.model.entity.Account.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @NotEmpty(message = "Full name is required")
//    @Size(max = 50, message = "Full name max is 50 characters")
//    @Column(name = "full_name", nullable = false, length = 50)
//    private String fullName;

    @NotEmpty(message = "Description is required")
    @Size(max = 500, message = "Description max is around 500 characters")
    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "experience_year",nullable = false)
    private int experienceYear;

    @NotEmpty(message = "Major is required")
    @Size(max = 50, message = "Major max is around 50 characters")
    @Column(name = "major")
    private String major;

    @NotEmpty(message = "Role is required")
    @Size(max = 10, message = "Role max is around 10 characters")
    @Column(name = "role", nullable = false, length = 10)
    private String role;

    @Column(name = "status", nullable = false,columnDefinition = "INT DEFAULT(0)")
    private int status;

    @Column(name = "managed_by")
    private int managedBy;

//    @Column(name = "activeSession")
//    private int activeSession;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account accountAdmin;

//    @OneToMany(mappedBy = "adminAdminProfile")
//    private List<AdminProfile> adminProfileList;

}
