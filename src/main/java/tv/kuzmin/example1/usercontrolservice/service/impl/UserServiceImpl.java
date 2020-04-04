package tv.kuzmin.example1.usercontrolservice.service.impl;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tv.kuzmin.example1.usercontrolservice.crypt.CryptPassword;
import tv.kuzmin.example1.usercontrolservice.model.User;
import tv.kuzmin.example1.usercontrolservice.repository.UserRepository;
import tv.kuzmin.example1.usercontrolservice.service.UserService;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CryptPassword cryptPassword;

    public List<User> findAll()
    {
        return userRepository.findAll().stream().map(user -> {
            user.setPassword(null);
            return user;
        }).collect(Collectors.toList());
    }


    public Optional<User> findById(Long id)
    {
        return userRepository.findById(id);
    }


    @Override
    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }


    public User create(User user)
    {
        if (Objects.nonNull(user.getPassword()))
        {
            user.setPassword(cryptPassword.encode(user.getPassword()));
        }
        userRepository.save(user);
        return user;
    }


    public User update(User user)
    {
        Optional<User> optionalUser = findById(user.getId());
        if (optionalUser.isPresent())
        {
            User savedUser = optionalUser.get();
            user.setUsername(savedUser.getUsername());
            String password = savedUser.getPassword();

            if (Objects.nonNull(user.getPassword()))
            {
                password = cryptPassword.encode(user.getPassword());
            }

            user.setPassword(password);
        }

        userRepository.save(user);

        return user;
    }


    public void deleteById(Long id)
    {
        userRepository.deleteById(id);
    }
}
