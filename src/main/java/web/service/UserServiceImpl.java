package web.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.RoleDAO;
import web.DAO.UserDAO;
import web.model.User;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public User getById(long id) {
        User user = null;
        Optional<User> optional = userDAO.findById(id);
        if(optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Override
    public User save(User user) {
      return  userDAO.save(passwordCoder(user));
    }

    @Override
    public User update(User user) {
        return userDAO.save(user);
    }

    @Override
    public void deleteById(long id) {
        userDAO.deleteById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}

