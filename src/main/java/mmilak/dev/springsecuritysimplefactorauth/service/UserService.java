package mmilak.dev.springsecuritysimplefactorauth.service;

import mmilak.dev.springsecuritysimplefactorauth.model.AppUser;
import mmilak.dev.springsecuritysimplefactorauth.model.Token;
import mmilak.dev.springsecuritysimplefactorauth.repo.AppUserRepo;
import mmilak.dev.springsecuritysimplefactorauth.repo.TokenRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

    private MailService mailService;
    private TokenRepo tokenRepo;
    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;

    public UserService(MailService mailService, TokenRepo tokenRepo, AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.mailService = mailService;
        this.tokenRepo = tokenRepo;
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole("ROLE_USER");
        appUserRepo.save(appUser);
        sendToken(appUser);
    }

    private void sendToken(AppUser appUser) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setAppUser(appUser);
        tokenRepo.save(token);

        String url = "http://localhost:8080/token?value=" + tokenValue;

        try {
            mailService.sendMail(appUser.getUsername(), "Potwierdzenie", url, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
