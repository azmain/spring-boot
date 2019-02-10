package azmain.github.io.controller.user;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import azmain.github.io.bean.network.RequestBean;
import azmain.github.io.bean.network.ResponseBean;
import azmain.github.io.helper.Token;
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
    
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/registration")
    public ResponseBean registrationAttempt(HttpServletRequest httpServletRequest, @RequestBody RequestBean requestBean) {

        ResponseBean responseBean = new ResponseBean();

        String token = Token.getToken();
        System.out.println("TOKEN:" + token);
        requestBean.getUserBean().setToken(token);

        System.out.println("Go:");
        ResponseBean registrationResponse = new UserManager().registrationUser(httpServletRequest, entityManagerFactory, requestBean.getUserBean());

        if (registrationResponse.getCode()==200){

            //Email email = new Email();
            //Response emailResponse = email.sendRegistrationSuccessEmail(javaMailSender,request.getUserBn(),httpServletRequest.getRemoteAddr());

			
			/*
			 * if (emailResponse.getCode()==200){ responseBean.setCode(200); responseBean.
			 * setMsg("A link has been sent to your email address, please check your mail !"
			 * ); }else { responseBean.setCode(400); responseBean.
			 * setMsg("No link sent to your email address to complete the registration !");
			 * }
			 */
			 
        	return registrationResponse;

        }else {
        	responseBean.setCode(400);
        	responseBean.setMsg("Registration unsuccessful !");
        }

        return responseBean;

    }

	
}
