package web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import web.DAO.RoleDAO;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Override
    public void run(ApplicationArguments arg0) throws Exception {
     //   System.out.println("Hello World from Application Runner");



        Set<Role> roles1 = new HashSet<>();
        roles1.add(roleDAO.findById(1L).orElse(null));
        Set<Role> roles2 = new HashSet<>();
        roles2.add(roleDAO.findById(1L).orElse(null));
        roles2.add(roleDAO.findById(2L).orElse(null));
        User user1 = new User("Ivan","Ivanov",(byte) 25, "ivan@mail.com", "user","12345",roles1);
        User user2 = new User("Sergey","Sergeev",(byte) 30, "sergey@mail.com", "admin","admin",roles2);
        userService.save(user1);
        userService.save(user2);
    }
}
