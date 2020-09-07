# spring-security-simple-factor-auth
Spring Security - Simple factor auth

Do zarządzania Spring Security została utworzona klasa WebSecurityConfig która dziedziczy po klasie WebSecurityConfigurerAdapter

W tej klasie nadpisujemy metody configure(AuthenticationManagerBuilder auth)
i metode configure(HttpSecurity http)

W metodzie configure(AuthenticationManagerBuilder) podajemy klasę która implementuje interfejs UserDetailsService. Tutaj będzie umieszczona logika która będzie sprawdzać czy wprowadzony przez użytkownika login i hasło jest prawidłowe.

W drugiej metodzie configure(HttpSecurity http) możemy określić jakie uprawnienia mają określeni użytkownicy. Każdy użytkownik ma przypisaną rolę (ROLE_ADMIN, ROLE_USER)
Na podstawie tej roli możemy w tej metodzie napisać logikę określającą prawa dostępu do konkretnego zasobu.


W klasie MailService jest prosta implementacja logiki wysyłania maili do zarejestrowanych użytkowników w celu potwierdzenia swojego adresu email. 

Do tego celu potrzebujemy konta email z którego będą wysyłane wiadomości. W tym celu dodajemy odpowiednią konfigurację do application.properties, gdzie podajemy również nasze dane do maila

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=todo
spring.mail.password=todo
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

Aby wysyłka maili działa trzeba wejść w opcje skrzynki pocztowej -> Bezpieczeństwo -> Dostęp mniej bezpiecznych aplikacji -> i tutaj Włącz dostę (Niezalecane)