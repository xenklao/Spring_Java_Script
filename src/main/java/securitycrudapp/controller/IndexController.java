package securitycrudapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import securitycrudapp.config.exception.LoginException;
import securitycrudapp.service.RoleService;
import securitycrudapp.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {
	private final UserService appService;
	private final RoleService roleService;
	@Autowired
	public IndexController(UserService appService, RoleService roleService) {
		this.appService = appService;
		this.roleService = roleService;
	}

	@GetMapping("")
	public String welcomePage(Model model, HttpSession session,
							  @SessionAttribute(required = false, name = "Authentication-Exception") LoginException authenticationException,
							  @SessionAttribute(required = false, name = "Authentication-Name") String authenticationName) {
		roleService.authenticateOrLogout(model, session, authenticationException, authenticationName);
		return "index";
	}

	@GetMapping("/403")
	public String accessDeniedPage() {
		return "access-denied";
	}
}