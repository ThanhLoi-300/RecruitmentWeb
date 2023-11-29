package com.example.springrestful.service.RecruitmentService;

import com.example.springrestful.model.entity.Recruiter.Recruiter;
import com.example.springrestful.model.mapper.RecruiterMapper;
import com.example.springrestful.model.request.RequestRecruiter.RequestRecruiter;
import com.example.springrestful.model.response.ResponseRecruiter.ResponseRecruiter;
import com.example.springrestful.repository.AccountRepository;
import com.example.springrestful.repository.RecruitmentRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {
    @Autowired
    RecruitmentRepository recruiterRepository;
    @Autowired
    RecruiterMapper recruiterMapper;
    @Autowired
    AccountRepository accountRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public ResponseRecruiter saveRecruiter(RequestRecruiter requestRecruiter) throws Exception {
        String pathLogo = writeImg(requestRecruiter.getLogo());

        Recruiter recruiter = new Recruiter();
        recruiter.setId(requestRecruiter.getId());
        recruiter.setFullName(requestRecruiter.getFullName());
        recruiter.setCompanyName(requestRecruiter.getCompanyName());
        recruiter.setLinkWebsite(requestRecruiter.getLinkWebsite());
        recruiter.setLinkFacebook(requestRecruiter.getLinkFacebook());
        recruiter.setCompanyAddress(requestRecruiter.getCompanyAddress());
        recruiter.setCompanyPhone(requestRecruiter.getCompanyPhone());
        recruiter.setLogo(pathLogo);

        recruiter.setCompanyDescription(requestRecruiter.getCompanyDescription());
        recruiter.setAccount(accountRepository.findById(requestRecruiter.getAccountId()));


        return recruiterMapper.toResponseRecruiter(recruiterRepository.save(recruiter));
    }

    @Override
    public ResponseRecruiter editRecruiter(RequestRecruiter requestRecruiter) throws Exception {
        String pathLogo = writeImg(requestRecruiter.getLogo());

        Recruiter recruiter = recruiterRepository.findById(requestRecruiter.getId());
        recruiter.setFullName(requestRecruiter.getFullName());
        recruiter.setCompanyName(requestRecruiter.getCompanyName());
        recruiter.setLinkWebsite(requestRecruiter.getLinkWebsite());
        recruiter.setLinkFacebook(requestRecruiter.getLinkFacebook());
        recruiter.setCompanyAddress(requestRecruiter.getCompanyAddress());
        recruiter.setCompanyPhone(requestRecruiter.getCompanyPhone());
        recruiter.setLogo(pathLogo);

        recruiter.setCompanyDescription(requestRecruiter.getCompanyDescription());
        recruiter.setAccount(accountRepository.findById(requestRecruiter.getAccountId()));

        return recruiterMapper.toResponseRecruiter(recruiterRepository.save(recruiter));
    }

    @Override
    public void removeRecruiterById(int id) throws Exception {
        Recruiter recruiter = recruiterRepository.findById(id);
        if (recruiter != null) {
            // Xóa nhà tuyển dụng sử dụng recruiterRepository
            recruiterRepository.delete(recruiter);
            System.out.println("Nhà tuyển dụng có ID " + id + " đã được xóa thành công.");
        } else {
            throw new Exception("Không tìm thấy nhà tuyển dụng với ID " + id);
        }
        recruiterRepository.save(recruiter);
    }

    @Override
    public List<ResponseRecruiter> findAllRecruiters() throws Exception {
        return recruiterMapper.toResponseRecruiterList(recruiterRepository.findAll());
    }

    @Override
    public ResponseRecruiter findRecruiterById(int id) throws Exception {
        return recruiterMapper.toResponseRecruiter(recruiterRepository.findById(id));
    }


    @Override
    public void contactWithCandidate(String to, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);

        mailSender.send(message);
    }

    @Override
    public List<ResponseRecruiter> followCandidate(int infoApplyStatus) throws Exception {
        String jpql = "SELECT n FROM Candicate n WHERE n.Info_Apply.Status = :1";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("infoApplyStatus", infoApplyStatus);

        return recruiterMapper.toResponseRecruiterList((List<Recruiter>) query.getResultList());
    }

    @Override
    public void updateLogoById(int id, String logoPath) {

    }

    public String writeImg(MultipartFile file) {
        try {
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
            String fileName = date + file.getOriginalFilename();

            String filePath = "D:\\A\\mock_project_final\\spring-webapp\\src\\main\\resources\\static\\images\\candidate\\avatar\\" + fileName;

            // Copies Spring's multipartfile inputStream to /sismed/temp/exames (absolute path)
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception ex) {
            return null;
        }
    }
}
