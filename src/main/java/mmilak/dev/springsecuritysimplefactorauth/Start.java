package mmilak.dev.springsecuritysimplefactorauth;

import mmilak.dev.springsecuritysimplefactorauth.model.AppUser;
import mmilak.dev.springsecuritysimplefactorauth.repo.AppUserRepo;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {

    private AppUserRepo appUserRepo;

    public Start(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;

        AppUser appUser = new AppUser();
        appUser.setUsername("Janusz");
        appUser.setPassword(passwordEncoder.encode("Janusz123"));
        appUser.setRole("ROLE_ADMIN");
        appUser.setEnable(true);
        appUserRepo.save(appUser);

        AppUser appUser1 = new AppUser();
        appUser1.setUsername("Bogdan");
        appUser1.setPassword(passwordEncoder.encode("Bogdan123"));
        appUser1.setRole("ROLE_USER");
        appUser1.setEnable(true);
        appUserRepo.save(appUser1);

    }


}
