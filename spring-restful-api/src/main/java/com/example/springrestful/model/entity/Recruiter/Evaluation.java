package com.example.springrestful.model.entity.Recruiter;
import com.example.springrestful.model.entity.Account.Account;
import com.example.springrestful.model.entity.Candidate.Candidate;
import com.example.springrestful.model.entity.Recruiter.Recruiter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 chars")
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @NotBlank(message = "Content is required")
    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String content;

    @Min(value = 0, message = "View number must be greater than or equal to 0")
    @Column(name = "number_like", nullable = false)
    private int numberLike;


    @NotNull(message = "Date created is required")
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @NotNull(message = "Date updated is required")
    @Column(name = "date_updated", nullable = false)
    private Date dateUpdated;

    @Min(value = 0, message = "View number must be greater than or equal to 0")
    @Column(name = "view_number", nullable = false)
    private int viewNumber;

    @Column(name = "status", nullable = false)
    private String status = "active";

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private Recruiter recruiter;

}
