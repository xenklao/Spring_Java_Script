package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.Exception.ExceptionInfo;
import web.Exception.ResourceNotFoundException;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin")///api
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(RoleService roleService, UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.of(
                Optional
                        .of(Optional.of(userService.save(user)))

                        .orElseThrow(() -> new DataIntegrityViolationException("User already exist"))
        );

//        if (bindingResult.hasErrors()) {
//            String error = getErrorsFromBindingResult(bindingResult);
//            return new ResponseEntity<>(new ExceptionInfo(error), HttpStatus.BAD_REQUEST);
//        }
//        try {
//            userService.save(user);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }catch (UserUsernameExistException u) {
//            throw new UserUsernameExistException("User with username exist");
//        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ExceptionInfo> pageDelete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(new ExceptionInfo("User deleted"), HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser (@PathVariable("id") long id) {
        //User user = userService.getById(id);
        User user = Optional.ofNullable(userService.getById(id)).orElseThrow(() -> new ResourceNotFoundException("User no found"));
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername (Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ExceptionInfo> pageEdit(@PathVariable("id") long id,
                         @Valid @RequestBody User user,
                         BindingResult bindingResult) {

        String oldPassword = userService.getById(id).getPassword();
        if (oldPassword.equals(user.getPassword())) {
            System.out.println("TRUE");
            user.setPassword(oldPassword);
            ResponseEntity.of(
                    Optional
                            .of(Optional.of(userService.update(user)))

                            .orElseThrow(() -> new DataIntegrityViolationException("User already exist")));
        } else {
            System.out.println("FALSE");
            ResponseEntity.of(
                    Optional
                            .of(Optional.of(userService.save(user)))

                            .orElseThrow(() -> new DataIntegrityViolationException("User already exist")));
        }
        return new ResponseEntity<>(HttpStatus.OK);

//        if (bindingResult.hasErrors()) {
//            String error = getErrorsFromBindingResult(bindingResult);
//            return new ResponseEntity<>(new ExceptionInfo(error), HttpStatus.BAD_REQUEST);
//        }
//        try {
//            String oldPassword = userService.getById(id).getPassword();
//            if (oldPassword.equals(user.getPassword())) {
//                System.out.println("TRUE");
//                user.setPassword(oldPassword);
//                userService.update(user);
//            } else {
//                System.out.println("FALSE");
//                userService.save(user);
//            }
//            return new ResponseEntity<>(HttpStatus.OK);
//        }catch (UserUsernameExistException u) {
//            throw new UserUsernameExistException("User with username exist");
//        }

    }

    private String getErrorsFromBindingResult(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
    }
}