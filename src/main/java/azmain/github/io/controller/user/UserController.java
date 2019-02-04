package azmain.github.io.controller.user;

import javax.persistence.EntityManagerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import azmain.github.io.bean.network.RequestBean;
import azmain.github.io.bean.network.ResponseBean;
import azmain.github.io.manager.user.UserManager;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@GetMapping("/read")
	public ResponseBean getUser() {
		return new UserManager().getAllUser(entityManagerFactory);
	}
	
    @PostMapping("/create")
    public ResponseBean manage(@RequestBody RequestBean requestBean){
        return new UserManager().create(entityManagerFactory,requestBean);
    }
	
}
