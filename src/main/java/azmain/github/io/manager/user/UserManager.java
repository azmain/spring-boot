package azmain.github.io.manager.user;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import azmain.github.io.bean.network.RequestBean;
import azmain.github.io.bean.network.ResponseBean;
import azmain.github.io.bean.user.UserBean;
import azmain.github.io.helper.PasswordHelper;
import azmain.github.io.model.user.User;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

public class UserManager {
	
	public ResponseBean getAllUser(EntityManagerFactory entityManagerFactory){

        ResponseBean responseBean = new ResponseBean();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("SELECT * FROM user",User.class);
        
        List<User> userList = query.getResultList();

        session.getTransaction().commit();
        session.close();

        if (userList.size()>0){
        	responseBean.setCode(200);
        	responseBean.setMsg("User list fetch successful !");
        	responseBean.setList(userList);
            return responseBean;
        }else {
        	responseBean.setCode(404);
        	responseBean.setMsg("User list fetch unsuccessful !");
            return responseBean;
        }

    }

	public ResponseBean create(EntityManagerFactory entityManagerFactory, RequestBean requestBean) {
		
		ResponseBean responseBean = new ResponseBean();
		
		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            User user = new User();
            user.setCreatedAt(new Date());
            user.setUsername(requestBean.getUserBean().getUsername());
            user.setCreatedBy(requestBean.getUserBean().getCreatedBy());
            user.setEmail(requestBean.getUserBean().getEmail());
            user.setIp(requestBean.getUserBean().getIp());
            session.save(user);

            tx.commit();

            responseBean.setCode(200);
            responseBean.setMsg("User create successful !");

        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
            responseBean.setMsg("Exception occurred !");
            responseBean.setCode(400);
        }finally{
            if(session!=null){
                session.close();
            }
        }

        return responseBean;
		
	}
	
	/* New User Registration */
	public ResponseBean registrationUser(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, UserBean userBean) {
		ResponseBean responseBean = new ResponseBean();
		
		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Query query = session.createNativeQuery("SELECT count(id) from User WHERE email = :userMail");
			query.setParameter("userMail", userBean.getEmail());
			System.out.println("GO");
			System.out.println(query.getResultList().get(0).toString());
			if(query.getResultList().get(0).toString().equals("0")) {
				User user = new User();
				user.setEmail(userBean.getEmail());
				user.setUsername(userBean.getUsername());
				user.setIsActive(userBean.getIsActive());
				user.setIsApproved(userBean.getIsApproved());
				user.setPassword(PasswordHelper.encryptPassword(userBean.getPassword()));
				user.setToken(userBean.getToken());
				user.setIp(httpServletRequest.getLocalAddr());
				user.setCreatedBy(userBean.getCreatedBy());
				user.setModifiedBy(userBean.getModifiedBy());
				user.setCreatedAt(new Date());
				user.setModifiedAt(new Date());
				user.setLastLogin((new Date()).toString());
				System.out.println(userBean.getEmail());
				System.out.println(userBean.getPassword());
				System.out.println(userBean.getUsername());
				System.out.println(userBean.getCreatedAt());
				System.out.println(userBean.getModifiedAt());
				session.save(user);
				
				
				responseBean.setCode(200);
				responseBean.setMsg("User Registration Completed. Please Verify Your Email.");
			}
			else {
				responseBean.setCode(200);
				responseBean.setMsg("The User Email Already Exists!");
			}
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			responseBean.setCode(200);
			responseBean.setMsg("Exception Occured.");
		} finally {
			if(session != null) {
				session.close();
			}
		}
		
		return responseBean;
	}
	
	
	
	
}
