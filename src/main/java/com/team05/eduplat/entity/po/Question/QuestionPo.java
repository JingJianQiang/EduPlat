package com.team05.eduplat.entity.po.Question;

import javax.persistence.*;

/**
 * @program: eduplat
 * @description: 题目持久对象
 * @author: Jing
 * @create: 2019-11-18 11:38
 **/
@Entity
@Table(name="questions")
public class QuestionPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(name = "question_type")
    private int type;

    @Column(name = "question_content")
    private String content;

    @Column(name = "question_option1")
    private String option1;
    @Column(name = "question_option2")
    private String option2;
    @Column(name = "question_option3")
    private String option3;
    @Column(name = "question_option4")
    private String option4;

    @Column(name = "question_answer")
    private String answer;

    @Column(name = "question_description")
    private String q_description;

    @Column(name = "question_tag1")
    private  String tag1;
    @Column(name = "question_tag2")
    private  String tag2;
    @Column(name = "question_tag3")
    private  String tag3;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "course_id")
    private Long course_id;

    @Column(name = "isdelete")
    private int isdelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQ_description() {
        return q_description;
    }

    public void setQ_description(String q_description) {
        this.q_description = q_description;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }
}
