/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Answer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author oswal
 */
@Stateless
public class AnswerFacade extends AbstractFacade<Answer> {

    @PersistenceContext(unitName = "ExamOnline-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerFacade() {
        super(Answer.class);
    }

    public String generateAnswerId() {
        Query query = em.createNamedQuery("Answer.findLast", Answer.class);
        try {
            Answer a = (Answer) query.setMaxResults(1).getResultList().get(0);
            String id = a.getId();
            if (!id.equals("A999999999")) {
                long number = Integer.parseInt(id.substring(1)) + 1;
                return "A" + String.format("%09d", number);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "A000000001";
        }
        return null;
    }
}
