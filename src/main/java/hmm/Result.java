package hmm;

import javax.inject.Named;
import javax.persistence.*;

@Named
@Entity
@Table(name = "results", schema = "public")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private long userId;
    @Column(name = "valuex")
    private float valueX;
    @Column(name = "valueY")
    private float valueY;
    @Column(name = "valueR")
    private int valueR;
    @Column(name = "answer")
    private String answer;
    @Column(name = "time")
    private String time;
    @Column(name = "workTime")
    private long workTime;

    public Result() { }

    public Result(float valueX, float valueY, int valueR, String answer, String time, long workTime) {
        this.valueX = valueX;
        this.valueY = valueY;
        this.valueR = valueR;
        this.answer = answer;
        this.time = time;
        this.workTime = workTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public float getValueX() {
        return valueX;
    }

    public void setValueX(float valueX) {
        this.valueX = valueX;
    }

    public float getValueY() {
        return valueY;
    }

    public void setValueY(float valueY) {
        this.valueY = valueY;
    }

    public int getValueR() {
        return valueR;
    }

    public void setValueR(int valueR) {
        this.valueR = valueR;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getWorkTime() {
        return workTime;
    }

    public void setWorkTime(long workTime) {
        this.workTime = workTime;
    }
}
