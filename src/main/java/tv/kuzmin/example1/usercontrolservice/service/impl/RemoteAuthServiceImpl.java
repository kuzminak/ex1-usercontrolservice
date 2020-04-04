package tv.kuzmin.example1.usercontrolservice.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import tv.kuzmin.example1.usercontrolservice.crypt.CryptPassword;
import tv.kuzmin.example1.usercontrolservice.service.UserService;
import tv.kuzmin.example1.usercontrolservice.model.User;
import tv.kuzmin.userauthcontrolapi.RemoteAuthService;
import tv.kuzmin.userauthcontrolapi.UserInfo;
import tv.kuzmin.userauthcontrolapi.UserInfoImpl;

import java.util.Objects;


public class RemoteAuthServiceImpl implements RemoteAuthService
{
    private final UserService userService;

    @Autowired
    private CryptPassword cryptPassword;

    public RemoteAuthServiceImpl(UserService userService)
    {
        this.userService = userService;
    }


    @Override
    public UserInfo login(String login, String password)
    {
        User user = userService.findByUsername(login);
        if (Objects.nonNull(user) && passwordValidator(user, password))
        {
            return userInfoBuilder(user);
        }
        return null;
    }


    private boolean passwordValidator(User user, String password)
    {
        return user.getPassword().equals(cryptPassword.encode(password));
    }


    private UserInfo userInfoBuilder(User user)
    {
        return UserInfoImpl.builder()
                .setUsername(user.getUsername())
                .setFamilyName(user.getFamilyName())
                .setGivenName(user.getGivenName())
                .setAddress(user.getAddress())
                .setDescription(user.getDescription())
                .build();
    }
}
