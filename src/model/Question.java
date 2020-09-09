package model;

/**
 *
 * This class represents the Question object
 */
public class Question {

    private int question_id;
    private String exam_id;
    private String question_text;
    private String option1, option2, option3;
    private int true_answer;
    private int student_answer = 0;

    protected Question() {

    }

    protected Question(int question_id, String exam_id, String question_text, String option1, String option2, String option3, int true_answer) {//initialize the Question
        this.question_id = question_id;
        this.exam_id = exam_id;
        this.question_text = question_text;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.true_answer = true_answer;
    }

    public int getQuestion_id() {//return question_id
        return question_id;
    }

    public String getExam_id() {//return exam_id
        return exam_id;
    }

    public String getQuestion_text() {//return question_text
        return question_text;
    }

    public String getOption1() {//return the first choice
        return option1;
    }

    public String getOption2() {//return the second choice
        return option2;
    }

    public String getOption3() {//return the third choice
        return option3;
    }

    public int getTrue_answer() {//return true_answer
        return true_answer;
    }

    public void setQuestion_id(int question_id) {//update question_id
        this.question_id = question_id;
    }

    public void setExam_id(String exam_id) {//update exam_id
        this.exam_id = exam_id;
    }

    public void setQuestion_text(String question_text) {//update question_text
        this.question_text = question_text;
    }

    public void setOption1(String option1) {//update the first choice
        this.option1 = option1;
    }

    public void setOption2(String option2) {//update the second choice
        this.option2 = option2;
    }

    public void setOption3(String option3) {//update the third choice
        this.option3 = option3;
    }

    public void setTrue_answer(int true_answer) {//update true_answer
        this.true_answer = true_answer;
    }

    public int getStudent_answer() {//return sutdent_answer
        return student_answer;
    }

    public void setStudent_answer(int student_answer) {//update student_answer
        this.student_answer = student_answer;
    }

    public String toString() {//return a String representaion of the Question object
        return ("Question[question_text: " + question_text + ", option1: " + option1 + ", option2: " + option2 + ", option3: " + option3 + ", true_answer: " + true_answer + "]");
    }
}
