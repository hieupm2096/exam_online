/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Exam;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author oswal
 */
@Stateless
public class ExamFacade extends AbstractFacade<Exam> {

    @PersistenceContext(unitName = "ExamOnline-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamFacade() {
        super(Exam.class);
    }
    
    public String generateExamId() {
        String findLast = "SELECT e FROM Exam e ORDER BY e.id DESC";
        Query query = em.createQuery(findLast, Exam.class);
        List<Exam> exams = query.setMaxResults(1).getResultList();
        if (exams != null) {
            String id = exams.get(0).getId();
            if (!id.equals("E999999")) {
                int number = Integer.parseInt(id.substring(1)) + 1;
                return "E" + String.format("%06d", number);
            }
        } else {
            return "E000001";
        }
        return null;
    }
}
