package tv.kuzmin.example1.usercontrolservice.service;


import tv.kuzmin.example1.usercontrolservice.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService
{
    List<User> findAll();


    Optional<User> findById(Long id);


    User findByUsername(String username);


    User create(User user);


    User update(User user);


    void deleteById(Long id);
}
