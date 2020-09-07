package mmilak.dev.springsecuritysimplefactorauth.repo;

import mmilak.dev.springsecuritysimplefactorauth.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {

    Token findByValue(String value);
}
