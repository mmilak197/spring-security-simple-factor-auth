package mmilak.dev.springsecuritysimplefactorauth.controller;

import mmilak.dev.springsecuritysimplefactorauth.model.AppUser;
import mmilak.dev.springsecuritysimplefactorauth.model.Token;
import mmilak.dev.springsecuritysimplefactorauth.repo.AppUserRepo;
import mmilak.dev.springsecuritysimplefactorauth.repo.TokenRepo;
import mmilak.dev.springsecuritysimplefactorauth.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@Controller
public class UserController {

    private UserService userService;

    private TokenRepo tokenRepo;

    private AppUserRepo appUserRepo;

    public UserController(UserService userService, TokenRepo tokenRepo, AppUserRepo appUserRepo) {
        this.userService = userService;
        this.tokenRepo = tokenRepo;
        this.appUserRepo = appUserRepo;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }


    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());

        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("authorities", authorities);

        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("details", details);

        return "hello";
    }

    @GetMapping("/sing-up")
    public String singUp(Model model) {
        model.addAttribute("user", new AppUser());
        return "sing-up";
    }

    @PostMapping("/register")
    public String register(AppUser appUser) {
        userService.addUser(appUser);
        return "hello";
    }

    @GetMapping("/token")
    public String singup(@RequestParam String value) {
        Token byValue = tokenRepo.findByValue(value);

        AppUser appUser = byValue.getAppUser();
        appUser.setEnable(true);

        appUserRepo.save(appUser);

        return "hello";
    }

}
