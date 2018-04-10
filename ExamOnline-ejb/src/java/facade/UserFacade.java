/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author oswal
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "ExamOnline-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User findUserByEmailAndPassword(String email, String password) {
        User user = null;
        if (email != null && password != null) {
            String findUserByEmailAndPassword = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";
            
        }
        // else  email or/and password is empty
        return user;
    }
}
