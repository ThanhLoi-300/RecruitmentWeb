package com.example.springrestful.model.request.RequestRecruiter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRecruiter {
    private int id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 chars")
    private String companyName;

    @NotBlank(message = "Address is required")
    private String companyAddress;

    @NotBlank(message = "Description is required")
    private String companyDescription;
    private String fullName;
    private String linkWebsite;
    private String linkFacebook;
    private String companyPhone;


    private int accountId;
    private MultipartFile logo;

}
