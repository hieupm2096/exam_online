/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author oswal
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e")
    , @NamedQuery(name = "Exam.findById", query = "SELECT e FROM Exam e WHERE e.id = :id")
    , @NamedQuery(name = "Exam.findByName", query = "SELECT e FROM Exam e WHERE e.name = :name")
    , @NamedQuery(name = "Exam.findByDuration", query = "SELECT e FROM Exam e WHERE e.duration = :duration")
    , @NamedQuery(name = "Exam.findByStartTime", query = "SELECT e FROM Exam e WHERE e.startTime = :startTime")
    , @NamedQuery(name = "Exam.findByNumOfQuestion", query = "SELECT e FROM Exam e WHERE e.numOfQuestion = :numOfQuestion")})
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "_id")
    private String id;
    @Size(max = 100)
    @Column(name = "_name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "_duration")
    private int duration;
    @Column(name = "_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "_num_of_question")
    private Integer numOfQuestion;
    @JoinTable(name = "examquestion", joinColumns = {
        @JoinColumn(name = "_exam_id", referencedColumnName = "_id")}, inverseJoinColumns = {
        @JoinColumn(name = "_question_id", referencedColumnName = "_id")})
    @ManyToMany
    private List<Question> questionList;
    @JoinColumn(name = "_course_id", referencedColumnName = "_id")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "_user_id", referencedColumnName = "_id")
    @ManyToOne
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exam")
    private List<ExamStudent> examStudentList;

    public Exam() {
    }

    public Exam(String id) {
        this.id = id;
    }

    public Exam(String id, int duration) {
        this.id = id;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getNumOfQuestion() {
        return numOfQuestion;
    }

    public void setNumOfQuestion(Integer numOfQuestion) {
        this.numOfQuestion = numOfQuestion;
    }

    @XmlTransient
    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public List<ExamStudent> getExamStudentList() {
        return examStudentList;
    }

    public void setExamStudentList(List<ExamStudent> examStudentList) {
        this.examStudentList = examStudentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Exam[ id=" + id + " ]";
    }
    
}
