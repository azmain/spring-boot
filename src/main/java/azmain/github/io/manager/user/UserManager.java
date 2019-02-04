package azmain.github.io.manager.user;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import azmain.github.io.bean.network.RequestBean;
import azmain.github.io.bean.network.ResponseBean;
import azmain.github.io.model.user.User;
import java.util.List;

import javax.persistence.Query;

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
            user.setCreatedAt(requestBean.getUserBean().getCreatedAt());
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
	
}
