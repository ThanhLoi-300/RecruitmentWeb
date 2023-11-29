package com.example.springrestful.controller;

import com.example.springrestful.exception.ApplicationException;
import com.example.springrestful.exception.NotFoundException;
import com.example.springrestful.exception.ValidationException;
import com.example.springrestful.model.request.RequestRecruiter.RequestRecruiter;
import com.example.springrestful.model.response.ApiResponse.ApiResponse;
import com.example.springrestful.model.response.ResponseCandidate.ResponseCandidate;
import com.example.springrestful.model.response.ResponseRecruiter.ResponseRecruiter;
import com.example.springrestful.service.AccountService.AccountService;
import com.example.springrestful.service.RecruitmentService.RecruitmentService;
import com.example.springrestful.util.ValidatorUtil;
import com.example.springrestful.validator.PaginationValidator.PaginationValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recruiter")
public class RecruiterController {
    @Autowired
    RecruitmentService recruiterService;

    @Autowired
    ValidatorUtil validatorUtil;
    @Autowired
    PaginationValidator paginationValidator;

    @GetMapping(value = "")
    public ResponseEntity<ApiResponse<List<ResponseRecruiter>>> returnAllRecruiter() throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.ok(recruiterService.findAllRecruiters());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<ResponseRecruiter>> returnRecruitersById(@PathVariable int id) throws Exception {
        try {
            if (recruiterService.findRecruiterById(id) == null) {
                throw new NotFoundException("Recruiter not found");
            }
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(recruiterService.findRecruiterById(id));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<ResponseRecruiter>> addRecruiter(@Valid @RequestBody RequestRecruiter requestRecruiter, BindingResult bindingResult) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            if (requestRecruiter == null) {
                throw new NotFoundException("Recruiter not found");
            }

            ResponseRecruiter responseRecruiter = recruiterService.saveRecruiter(requestRecruiter);
            apiResponse.ok(responseRecruiter);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseRecruiter>> editRecruiter(
            @PathVariable int id, @Valid @RequestBody RequestRecruiter requestRecruiter,
            BindingResult bindingResult) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            requestRecruiter.setId(id);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }

            if (recruiterService.findRecruiterById(id) == null) {
                throw new NotFoundException("Recruiter not found");
            }

            ResponseRecruiter responseRecruiter = recruiterService.editRecruiter(requestRecruiter);
            apiResponse.ok(responseRecruiter);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRecruiter(@PathVariable int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            ResponseRecruiter responseRecruiter = recruiterService.findRecruiterById(id);
            if (responseRecruiter == null) {
                throw new NotFoundException("News not found");
            }
            recruiterService.removeRecruiterById(id);
            apiResponse.ok("Delete successfully");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }

    @PostMapping("/candidate")
    public ResponseEntity<ApiResponse<List<ResponseRecruiter>>> returnCandidateByFollow(
            @PathVariable int infoApplyStatus,
            @Valid @RequestBody RequestRecruiter requestRecruiter,
            BindingResult bindingResult) throws Exception {

        ApiResponse apiResponse = new ApiResponse();
        try {
            paginationValidator.validate(requestRecruiter, bindingResult);

            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = validatorUtil.toErrors(bindingResult.getFieldErrors());
                throw new ValidationException(validationErrors);
            }
            if (recruiterService.followCandidate(infoApplyStatus) == null) {
                throw new NotFoundException("Candidate not found");
            }

            apiResponse.ok(recruiterService.followCandidate(infoApplyStatus));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex; // Rethrow NotFoundException
        } catch (ValidationException ex) {
            throw ex; // Rethrow ValidationException
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage()); // Handle other exceptions
        }
    }
    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/saveRecruiterFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<ResponseRecruiter> saveRecruiterFile(@RequestParam("logo") MultipartFile logo,@RequestParam("id") int id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        try {
            StringBuilder filepathLogo = new StringBuilder("D:\\A\\mock_project_final\\spring-webapp\\src\\main\\resources\\static\\images\\recruiter\\logo\\");
            String logoFileName = logo.getOriginalFilename();

            filepathLogo.append(logoFileName);
            String logoPath = filepathLogo.toString();


            // Save CV file
            logo.transferTo(new File(logoPath));
            recruiterService.updateLogoById(id, logoPath) ;

            // Perform other operations with the email parameter if needed
            apiResponse.setMessage("Recruiter files uploaded successfully.");
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
