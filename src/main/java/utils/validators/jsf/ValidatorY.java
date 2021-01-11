package utils.validators.jsf;
import javax.faces.component.UIComponent;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import javax.faces.validator.Validator;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validatorY")
public class ValidatorY implements Validator
{
    public ValidatorY() {}

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException
    {
        try {
            double val = Double.parseDouble(value.toString());

            if (val >= 3 || val <= -5) {
                FacesMessage msg = new FacesMessage("Value Y is out of range.");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }

        } catch (NumberFormatException ex) {
            FacesMessage msg = new FacesMessage("Value Y is incorrect.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
