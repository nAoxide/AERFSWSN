package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * This class represents the Exam object
 */
public class Exam {

    private String exam_id;
    private String course_id;
    private List<Question> questions;
    private CustomListIterator<Question> iterator;
    private Connection connection;

    public Exam(String id) throws SQLException {//initialize the Exam and fill it with Questions
        this.exam_id = id;
        this.questions = new ArrayList<>();
        this.connection = connections.DBConnection.getConnection();
        String course_id = "";
        PreparedStatement statement = connection.prepareStatement("select course_id from exam where exam_id = ?");
        statement.setString(1, this.exam_id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            course_id = resultSet.getString("course_id");
        }
        this.course_id = course_id;
        statement = connection.prepareStatement("select * from question where exam_id = ?");
        statement.setString(1, exam_id);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Question question = new Question();
            int question_id = resultSet.getInt("question_id");
            String exam_id = resultSet.getString("exam_id");
            String question_text = resultSet.getString("question_text");
            String option1 = resultSet.getString("option1");
            String option2 = resultSet.getString("option2");
            String option3 = resultSet.getString("option3");
            int answer = resultSet.getInt("true_answer");
            this.course_id = course_id;
            question.setQuestion_id(question_id);
            question.setExam_id(exam_id);
            question.setQuestion_text(question_text);
            question.setOption1(option1);
            question.setOption2(option2);
            question.setOption3(option3);
            question.setTrue_answer(answer);
            this.addQuestion(question);
        }
        this.iterator = new CustomListIterator<>(this.questions);
    }

    public String getExam_id() {//return exam_id
        return exam_id;
    }

    public String getCourse_id() {//return course_id
        return course_id;
    }

    public void setExam_id(String exam_id) {//update exam_id
        this.exam_id = exam_id;
    }

    public void addQuestion(Question question) {//add a new Question
        this.questions.add(question);
    }

    public boolean hasNext() {//check for next Question
        return iterator.hasNext();
    }

    public boolean hasPrevious() {//check for previous Question
        return iterator.hasPrevious();
    }

    public Question getCurrent() {//return current Question
        return iterator.current();
    }

    public Question getNextQuestion() {//return next Question
        return iterator.next();
    }

    public Question getPreviousQuestion() {//return previous Question
        return iterator.previous();

    }

    public List<Question> getQuestions() {//return the Question list
        return questions;
    }

    public String toString() {//return a String represntation of the Exam object
        return "Exam[exam_id: " + exam_id + ", course_id: " + course_id + ", No. of quesions: " + questions.size() + "]";
    }
}
