package com.example.springrestful.model.entity.Recruiter;

public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 chars")
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 chars")
    @Column(name = "company_name", length = 100, nullable = false)
    private String companyName;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 chars")
    @Column(name = "link_website", length = 100, nullable = false)
    private String linkWebsite;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 chars")
    @Column(name = "link_facebook", length = 100, nullable = false)
    private String linkFacebook;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 chars")
    @Column(name = "company_phone", length = 100, nullable = false)
    private String companyPhone;

    @NotBlank(message = "Content is required")
    @Column(name = "company_address", columnDefinition = "LONGTEXT")
    private String companyAddress;

    @NotBlank(message = "Content is required")
    @Column(name = "company_description", columnDefinition = "LONGTEXT")
    private String companyDescription;

    @Column(name = "logo", unique = false, nullable = false, length = 100000)
    private String logo;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

//    @OneToMany(mappedBy = "recruiter")
//    private List<Job> jobList;

//    @OneToMany(mappedBy = "recruiter")
//    private List<Evaluation> evaluationList;

}
