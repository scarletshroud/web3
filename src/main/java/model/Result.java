package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "result")
public class Result {

    @Id
    private String userId;
    private float valueX;
    private float valueY;
    private int valueR;
    private String answer;
    private String time;
    private long workTime;

    public Result(){}

    public Result(float valueX, float valueY, int valueR, String answer, String time, long workTime) {
        this.valueX = valueX;
        this.valueY = valueY;
        this.valueR = valueR;
        this.answer = answer;
        this.time = time;
        this.workTime = workTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
