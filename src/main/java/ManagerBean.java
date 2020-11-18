import dao.ResultsTableDAO;
import dao.ResultsTableDAOImpl;
import model.Result;
import utils.DataFormatter;
import utils.HitChecker;
import utils.Validator;
import utils.exceptions.UnknownValueException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ManagedBean(name = "manager", eager = true)
@SessionScoped
public class ManagerBean {

    private String valueX;
    private String valueY;
    private String valueR;

    private String errorMessage;

    public void setValueX(String value){
       valueX = value;
    }

    public String getValueX() {
        return valueX;
    }

    public void setValueY(String value){
        valueY = value;
    }

    public String getValueY() {
        return valueY;
    }

    public void setValueR(String value){
        valueR = value;
    }

    public String getValueR() {
        return valueR;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String handleRequest() {
        long startTime = System.currentTimeMillis();
        errorMessage = "";

        try {
            errorMessage += Validator.isValid(valueX, "x");
            errorMessage += Validator.isValid(valueY, "y");
            errorMessage += Validator.isValid(valueR, "r");
        } catch (UnknownValueException ex) {
            errorMessage = "Server Error";
            return "main_page?faces-redirect=true";
        }

        if (errorMessage.equals("")) {
            String answer = HitChecker.isInArea(valueX, valueY, valueR) ? "In the zone" : "Out of zone";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            Result result = new Result(
                    Float.parseFloat(DataFormatter.formatValue(valueX)),
                    Float.parseFloat(DataFormatter.formatValue(valueY)),
                    Integer.parseInt(valueR),
                    answer,
                    formatter.format(LocalDateTime.now()),
                    System.currentTimeMillis() - startTime);

            ResultsTableDAO table = new ResultsTableDAOImpl();
            table.save(result);
        }

        return "main_page?faces-redirect=true";

    }

}
