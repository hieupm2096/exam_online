/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Answer;
import entity.Course;
import entity.Question;
import entity.QuestionType;
import facade.AnswerFacade;
import facade.CourseFacade;
import facade.QuestionFacade;
import facade.QuestionTypeFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author oswal
 */
@Named(value = "questionBean")
@RequestScoped
public class QuestionBean implements Serializable {

    @EJB
    private AnswerFacade answerFacade;

    @EJB
    private QuestionFacade questionFacade;

    @EJB
    private QuestionTypeFacade questionTypeFacade;

    @EJB
    private CourseFacade courseFacade;

    private List<QuestionType> questionTypeList;
    private List<Course> courseList;
    private List<Answer> answerList;

    private String questionTypeId;
    private String content;
    private String courseId;

    private String[][] answers;

    public List<QuestionType> getQuestionTypeList() {
        return questionTypeFacade.findAll();
    }

    public List<Course> getCourseList() {
        return courseFacade.findAll();
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public String getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(String questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String[][] getAnswers() {
        return answers;
    }

    @PostConstruct
    public void init() {
        answers = new String[5][2];
    }

    public QuestionBean() {

    }

    public String createQuestion() {
        Question q = new Question();
        q.setId(questionFacade.generateQuestionId());
        q.setContent(content);
        q.setStatus(true);
        q.setQuestionTypeId(questionTypeFacade.find(questionTypeId));
        q.setCourseId(courseFacade.find(courseId));
        questionFacade.create(q);

        for (String[] answer : answers) {
            if (answer != null && !answer[0].isEmpty()) {
                createAnswer(answer[0], Boolean.parseBoolean(answer[1]), q);
            }
        }

        return "index?faces-redirect=true";
    }

    private void createAnswer(String content, boolean isCorrect, Question question) {
        String id = answerFacade.generateAnswerId();
        Answer answer = new Answer(id, content, isCorrect, question);
        answerFacade.create(answer);
    }
}
