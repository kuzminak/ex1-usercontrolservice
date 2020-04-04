package tv.kuzmin.example1.usercontrolservice.web.api;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tv.kuzmin.example1.usercontrolservice.model.User;
import tv.kuzmin.example1.usercontrolservice.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserAPI
{
    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll()
    {
        return ResponseEntity.ok(userService.findAll());
    }


    @PostMapping
    public ResponseEntity create(@Valid @RequestBody User user)
    {
        return ResponseEntity.ok(userService.create(user));
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id)
    {
        Optional<User> optionalUser = userService.findById(id);
        if (!optionalUser.isPresent())
        {
            log.error("User Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        User user = optionalUser.get();
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user)
    {
        if (!userService.findById(id).isPresent())
        {
            log.error("User Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(userService.update(user));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id)
    {
        if (!userService.findById(id).isPresent())
        {
            log.error("User Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        userService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
