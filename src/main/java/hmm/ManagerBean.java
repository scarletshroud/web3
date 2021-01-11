package hmm;

import dao.ResultsTableDAO;
import dao.ResultsTableDAOImpl;
import utils.DataFormatter;
import utils.HitChecker;
import utils.validators.*;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ManagerBean implements Serializable {

    private String valueX;
    private String valueY;
    private String valueR;
    private StringBuilder errorMessage;

    private boolean isSuccessful;
    private boolean[] checkboxesR;

    private List<Result> resultList;

    @PostConstruct
    private void postConstruct() {
        valueX = "";
        valueY = "";
        valueR = "5";
        isSuccessful = true;
        checkboxesR = new boolean[] {false, false, false, false, true};
        ResultsTableDAO table = new ResultsTableDAOImpl();
        resultList = table.getAll();
    }

    public String getValueX() {
        return valueX;
    }

    public void setValueX(String valueX) {
        this.valueX = valueX;
    }

    public String getValueY() {
        return valueY;
    }

    public void setValueY(String valueY) {
        this.valueY = valueY;
    }

    public String getValueR() {
        return valueR;
    }

    public void setValueR(String valueR) {
        this.valueR = valueR;
    }

    public StringBuilder getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(StringBuilder errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public boolean[] getCheckboxesR() {
        return checkboxesR;
    }

    public void setCheckboxesR(boolean[] checkboxesR) {
        this.checkboxesR = checkboxesR;
    }

    public String handleRequest() {
        long startTime = System.currentTimeMillis();
        errorMessage = new StringBuilder();

        isSuccessful = (CheckboxesValidator.validate(checkboxesR, valueR, errorMessage) &&
                        new ValidatorX().validate(valueX, errorMessage) &&
                        new ValidatorY().validate(valueY, errorMessage) &&
                        new ValidatorR().validate(valueR, errorMessage));

        if(isSuccessful) {
            handleData(startTime);
        }

        return "main_page?faces-redirect=true";
    }

    public String handleRequestFromPic() {
        long startTime = System.currentTimeMillis();
        errorMessage = new StringBuilder();

        if (!(new ValidatorX().isEmpty(valueX, "X", errorMessage)) &&
                !(new ValidatorY().isEmpty(valueY, "Y", errorMessage)) &&
                new ValidatorR().validate(valueR, errorMessage)) {
            isSuccessful = true;
            handleData(startTime);
        } else {
            isSuccessful = false;
        }

        return "main_page?faces-redirect=true";
    }

    public void handleData(long time) {
        String answer = HitChecker.isInArea(valueX, valueY, valueR) ? "In the zone" : "Out of zone";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Result result = new Result(
                Float.parseFloat(DataFormatter.formatValue(valueX)),
                Float.parseFloat(DataFormatter.formatValue(valueY)),
                Integer.parseInt(valueR),
                answer,
                formatter.format(LocalDateTime.now()),
                System.currentTimeMillis() - time);

        ResultsTableDAO table = new ResultsTableDAOImpl();
        table.save(result);

        resultList.add(result);
    }

    public String clearAll() {
        ResultsTableDAO table = new ResultsTableDAOImpl();
        table.deleteAll();
        resultList.clear();
        return "main_page?faces-redirect=true";
    }
}
