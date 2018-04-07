/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author oswal
 */
@Named(value = "examBean")
@RequestScoped
public class ExamBean {

    private String name;
    private int numOfQuestion;
    private int duration;

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

    public ExamBean() {
    }

}
