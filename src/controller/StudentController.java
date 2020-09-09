package controller;

import connections.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Exam;
import model.Student;
import view.ExamView;
import view.StudentView;

/**
 *
 * This class represents the controller that deals with user authentication and authorization process
 */
public class StudentController {

    private Connection connection;
    private Student student;
    private StudentView view;
    public StudentController() {
        this.connection = DBConnection.getConnection();
    }

    public boolean checkStudentLogin(String username, String password) throws SQLException {//verify user login information
        PreparedStatement statement = connection.prepareStatement("select count(*) from login where user_id = ? AND password = ?");
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            int count = result.getInt(1);
            if (count == 0) {
                return false;
            }
        }
        return true;
    }
    private Student createStudent(String id) throws SQLException{//initialize a Student object
        Student student = new Student(id);
        return student;
    }
    public void authorizeStudent(String id) throws SQLException{//authorize the user to view the list of available exams
        this.student = createStudent(id);
        this.view = new StudentView();
        this.view.setController(this);
        this.view.setStudentName(student.getStudent_name());
        for(String exam_id: this.student.getExams()){
            this.view.addExamToList(exam_id);
        }
                this.view.setVisible(true);

    }
    public void attemptExam(String exam_id) throws SQLException{//view the exam selected by the user
        Exam exam = new Exam(exam_id);
        ExamView examView = new ExamView();
        ExamController examController = new ExamController(exam, examView);
        examController.setStudent_id(student.getStudent_id());
        examController.nextQuestion();
    }

    public StudentView getView() {//return StudentView
        return view;
    }

    public void setView(StudentView view) {//set StudentView
        this.view = view;
    }
}
