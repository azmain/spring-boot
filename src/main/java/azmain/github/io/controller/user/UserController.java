package azmain.github.io.controller.user;

import javax.persistence.EntityManagerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import azmain.github.io.manager.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@GetMapping("/get")
	public String getUser() {
		return new UserManager().getAllUser(entityManagerFactory);
	}
}
