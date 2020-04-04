package tv.kuzmin.example1.usercontrolservice.crypt;


import org.springframework.security.crypto.bcrypt.BCrypt;


public class CryptPassword
{
    private final String salt;

    public CryptPassword(String salt)
    {
        this.salt = salt;
    }


    public String encode(String password)
    {
        return BCrypt.hashpw(password, salt);
    }
}
