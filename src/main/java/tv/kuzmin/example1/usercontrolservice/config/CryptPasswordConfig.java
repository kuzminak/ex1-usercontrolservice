package tv.kuzmin.example1.usercontrolservice.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tv.kuzmin.example1.usercontrolservice.crypt.CryptPassword;


@Configuration
public class CryptPasswordConfig
{
    @Bean
    public CryptPassword cryptPassword(@Value("${user-auth.crypt-password.salt}") String salt)
    {
        return new CryptPassword(salt);
    }
}
