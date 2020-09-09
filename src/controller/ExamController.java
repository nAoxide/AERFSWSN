package controller;

import connections.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import narrator.*;
import model.Exam;
import model.Question;
import view.ExamView;

/**
 *
 * This class controls the exam submission process, by receiving input from the GUI.
 */
public class ExamController {

    private Exam model;
    private ExamView view;
    private Narrator narrator;
    private String student_id;
    private String course_id;

    public ExamController(Exam model, ExamView view) {//initialize the Object and assign variables
        this.narrator = Narrator.getNarrator();
        this.model = model;
        this.course_id = model.getCourse_id();
        this.view = view;
        view.setController(this);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ExamController.this.view.setVisible(true);
            }
        });

    }

    private void viewQuestion(Question question) {//update ExamView to print the Question object
        view.setQuestionText(question.getQuestion_text());
        view.setOption1(question.getOption1());
        view.setOption2(question.getOption2());
        view.setOption3(question.getOption3());
        view.setChoice(question.getStudent_answer());
    }

    public void nextQuestion() {//view the next question
        if (model.hasNext()) {
            Question q = model.getNextQuestion();
            viewQuestion(q);
            narrator.narrate("السؤال رقمْ" + String.valueOf(q.getQuestion_id()), "newQuestionNotification" + "_" + String.valueOf(q.getQuestion_id()));
        } else {
            noMoreQuestionsNotification();
        }
    }

    public void prevoiusQuestion() {//view the previous question
        if (model.hasPrevious()) {
            Question q = model.getPreviousQuestion();
            viewQuestion(q);
            narrator.narrate("السؤال رقمْ" + String.valueOf(q.getQuestion_id()), "newQuestionNotification" + "_" + String.valueOf(q.getQuestion_id()));
        } else {
            firstQuestionNotification();
        }
    }

    public void updateAnswer(int choice) {//update the current answer
        Question q = model.getCurrent();
        q.setStudent_answer(choice);
        view.setChoice(choice);
    }

    public void readQuestion() {//read the current question
        Question q = model.getCurrent();
        String text = q.getQuestion_text();
        String elementFullID = q.getExam_id() + "_" + q.getQuestion_id();
        narrator.narrate(text, elementFullID);
    }

    public void readOption1() {//read the first choice
        Question q = model.getCurrent();
        String text = q.getOption1();
        String elementFullID = q.getExam_id() + "_" + q.getQuestion_id() + "_1";
        narrator.narrate(text, elementFullID);
    }

    public void readOption2() {//read the second choice
        Question q = model.getCurrent();
        String text = q.getOption2();
        String elementFullID = q.getExam_id() + "_" + q.getQuestion_id() + "_2";
        narrator.narrate(text, elementFullID);
    }

    public void readOption3() {//read the third choice
        Question q = model.getCurrent();
        String text = q.getOption3();
        String elementFullID = q.getExam_id() + "_" + q.getQuestion_id() + "_3";
        narrator.narrate(text, elementFullID);
    }

    public void setStudent_id(String student_id) {//set student_id
        this.student_id = student_id;
    }

    public void setCourse_id(String course_id) {//set course_id
        this.course_id = course_id;
    }

    public String getStudent_id() {//return student_id
        return this.student_id;
    }

    public String getCourse_id() {//return course_id
        return this.course_id;
    }

    public void firstQuestionNotification() {//run a voice message notifying the user there is no previous questions
        narrator.narrate("هذا هو السؤال الأول", "firstQuestionNotification");
    }

    public void noMoreQuestionsNotification() {//run a voice message notifying the user there is no more questions
        narrator.narrate("لا يوجد مزيد من الأسئلة", "noMoreQuestionsNotification");
    }

    public void saveAttempt() throws SQLException {//Store student answers into the Database, and get back to the Exam list
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement;
        String student_id = this.student_id;
        String course_id = this.course_id;
        String exam_id = this.model.getExam_id();
        int question_id, true_answer, student_answer;
        for (Question question : model.getQuestions()) {
            question_id = question.getQuestion_id();
            true_answer = question.getTrue_answer();
            student_answer = question.getStudent_answer();
            statement = connection.prepareStatement("insert into attempt(student_id, course_id, exam_id, question_id, true_answer, student_answer) values(?, ?, ?, ?, ?, ?)");
            statement.setString(1, student_id);
            statement.setString(2, course_id);
            statement.setString(3, exam_id);
            statement.setInt(4, question_id);
            statement.setInt(5, true_answer);
            statement.setInt(6, student_answer);
            statement.execute();

        }
        backToMainMenu();
    }

    private void backToMainMenu() throws SQLException {//get back to the Exam list
        new StudentController().authorizeStudent(student_id);
    }

}
