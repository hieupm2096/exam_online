/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Question;
import entity.Student;
import facade.ClassFacade;
import facade.ExamFacade;
import facade.QuestionFacade;
import facade.StudentFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author oswal
 */
@Named(value = "examBean")
@ViewScoped
public class ExamBean implements Serializable {

    @EJB
    private QuestionFacade questionFacade;

    @EJB
    private ClassFacade classFacade;

    @EJB
    private StudentFacade studentFacade;

    @EJB
    private ExamFacade examFacade;

    private String name;
    private int numOfQuestion;
    private int duration;
    private List<Student> students;
    private List<Question> questions;

    private String studentId;
    private String classId;

    private String questionId;
    private String courseId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }

    public void setNumOfQuestion(int numOfQuestion) {
        this.numOfQuestion = numOfQuestion;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        questions = new ArrayList<>();
    }

    public ExamBean() {

    }

    public void addStudentToList() {
        Student student = null;
        boolean isContained = false;
        if (studentId != null) {
            if (students != null && !students.isEmpty()) {
                for (Student s : students) {
                    if (s.getId().equals(studentId)) {
                        isContained = true;
                        break;
                    }
                }
                if (!isContained) {
                    student = studentFacade.findAvailableStudentById(studentId);
                }
            } else {
                student = studentFacade.findAvailableStudentById(studentId);
            }
            if (student != null) {
                students.add(student);
            }
            // else student is not found
        }
        // else studentId is empty
    }

    public void addClassToList() {
        if (classId != null) {
            entity.Class c = classFacade.find(classId);
            if (c != null) {
                List<Student> classStudents = c.getStudentList();
                if (classStudents != null && !classStudents.isEmpty()) {
                    if (students != null && !students.isEmpty()) {
                        for (Student student : classStudents) {
                            if (!students.contains(student) && student.getStatus()) {
                                students.add(student);
                            }
                        }
                    } else {
                        students = classStudents;
                    }
                }
                // else class has no student
            }
            // else classId not found
        }
        // class Id input is empty
    }
    
    public void addQuestionToList() {
        if (questionId != null) {
            
        }
        // else questionId is empty
    }
    
    public void addQustionToListAuto() {
        
    }
}
