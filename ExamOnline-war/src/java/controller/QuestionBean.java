/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Answer;
import entity.Course;
import entity.QuestionType;
import facade.CourseFacade;
import facade.QuestionFacade;
import facade.QuestionTypeFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author oswal
 */
@Named(value = "questionBean")
@RequestScoped
public class QuestionBean implements Serializable{

    @EJB
    private CourseFacade courseFacade;

    @EJB
    private QuestionTypeFacade questionTypeFacade;

    @EJB
    private QuestionFacade questionFacade;
    
    private List<QuestionType> typeList;
    private List<Course> courseList;
    private List<Answer> answerList;
    
    private QuestionType questionType;
    private String content;
    private int point;
    private Course course;

    public List<QuestionType> getTypeList() {
        return questionTypeFacade.findAll();
    }

    public List<Course> getCourseList() {
        return courseFacade.findAll();
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public QuestionBean() {
    }

    
}
