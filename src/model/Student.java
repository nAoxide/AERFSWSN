package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * This class represents the Student object
 */
public class Student {

    private String student_id;
    private String student_name;
    private String department;
    private String college;
    private String health_status;
    private int level;
    private List<String> courses;
    private List<String> exams;
    private Connection connection;

    public Student(String id) throws SQLException {//initialize the Student object by fetching info from database and assigning variables
        this.student_id = id;
        this.courses = new ArrayList<>();
        this.exams = new ArrayList<>();
        this.connection = connections.DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from student where student_id = ?");
        statement.setString(1, student_id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            this.student_name = resultSet.getString("student_name");
            this.department = resultSet.getString("department");
            this.college = resultSet.getString("college");
            this.health_status = resultSet.getString("health_status");
            this.level = resultSet.getInt("level");
        }
        statement = connection.prepareStatement("select course_id from register where student_id = ?");
        statement.setString(1, student_id);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String course = resultSet.getString("course_id");
            this.addCourse(course);
        }
        for (String course : courses) {
            statement = connection.prepareStatement("select exam_id from exam where course_id = ? and exam_id not in ( select exam_id from attempt where student_id = ? )");
            statement.setString(1, course);
            statement.setString(2, this.student_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String exam = resultSet.getString("exam_id");
                this.addExam(exam);
            }
        }

    }

    public String getStudent_id() {//return student_id
        return student_id;
    }

    public void setStudent_id(String student_id) {//update student_id
        this.student_id = student_id;
    }

    public String getStudent_name() {//return student_name
        return student_name;
    }

    public void setStudent_name(String student_name) {//update student_name
        this.student_name = student_name;
    }

    public String getDepartment() {//return department
        return department;
    }

    public void setDepartment(String department) {//update student department
        this.department = department;
    }

    public String getCollege() {//return college
        return college;
    }

    public void setCollege(String college) {//update student department
        this.college = college;
    }

    public String getHealth_status() {//return health_status
        return health_status;
    }

    public void setHealth_status(String health_status) {//update student health_status
        this.health_status = health_status;
    }

    public int getLevel() {//return student's level
        return level;
    }

    public void setLevel(int level) {//update stuednt level
        this.level = level;
    }

    public List<String> getCourses() {//return list for registered courses
        return courses;
    }

    public void addCourse(String course) {//add a new registered course
        this.courses.add(course);
    }

    public List<String> getExams() {//return list of available exams
        return exams;
    }

    public void addExam(String exam) {//add an exam to the exam list
        this.exams.add(exam);
    }
}
