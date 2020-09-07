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
        appUser.setUsername("admin");
        appUser.setPassword(passwordEncoder.encode("admin123"));
        appUser.setRole("ROLE_ADMIN");
        appUser.setEnable(true);
        appUserRepo.save(appUser);

        AppUser appUser1 = new AppUser();
        appUser1.setUsername("user");
        appUser1.setPassword(passwordEncoder.encode("user123"));
        appUser1.setRole("ROLE_USER");
        appUser1.setEnable(true);
        appUserRepo.save(appUser1);

    }


}
