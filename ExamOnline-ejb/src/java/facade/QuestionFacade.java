/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Question;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author oswal
 */
@Stateless
public class QuestionFacade extends AbstractFacade<Question> {

    @PersistenceContext(unitName = "ExamOnline-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionFacade() {
        super(Question.class);
    }
    
    public String generateQuestionId() {
        Query query = em.createNamedQuery("Question.findLast", Question.class);
        try {
            Question q = (Question) query.setMaxResults(1).getResultList().get(0);
            String id = q.getId();
            if (!id.equals("Q999999")) {
                int number = Integer.parseInt(id.substring(1)) + 1;
                return "Q" + String.format("%06d", number);
}
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Q000001";
        }
        return null;
    }
}
