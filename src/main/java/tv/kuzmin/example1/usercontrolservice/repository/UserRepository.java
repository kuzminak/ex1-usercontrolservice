package tv.kuzmin.example1.usercontrolservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tv.kuzmin.example1.usercontrolservice.model.User;


public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);
}
