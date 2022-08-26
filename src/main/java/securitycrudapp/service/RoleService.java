package securitycrudapp.service;

import org.springframework.ui.Model;
import securitycrudapp.config.exception.LoginException;
import securitycrudapp.model.Role;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface RoleService {

    List<Role> findAllRoles();

    void authenticateOrLogout(Model model, HttpSession session, LoginException authenticationException, String authenticationName);


}
