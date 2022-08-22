package crudapp.service;

import crudapp.model.User;
import crudapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

//    @Transactional(readOnly = true)
//    @Override
//    public void createOrUpdateUser(User user) {
//        if (0 == user.getId()) {
//            createUser(user);
//        } else {
//            updateUser(user);
//        }
//    }
    @Transactional
    @Override
    public void createUser(User user) {
        userRepository.createUser(user);
    }
    @Transactional
    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
    @Transactional(readOnly = true)
    @Override
    public User readUser(long id) {
        return userRepository.readUser(id);
    }

    @Transactional
    @Override
    public User deleteUser(long id) {
        User user = null;
        try {
            user = userRepository.deleteUser(id);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return user;
    }
}
