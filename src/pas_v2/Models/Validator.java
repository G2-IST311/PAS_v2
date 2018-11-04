package pas_v2.Models;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class Validator implements ChangeListener {

    TextField fld;
    FieldTypeEnum type;

    public Validator(TextField fld, FieldTypeEnum type) {
        this.fld = fld;
        this.type = type;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {

        switch (type) {

            case LOGIN:
                if (!((String) newValue).matches("[A-Za-z]*")) {
                    fld.setText((String) oldValue);
                }
                break;
            case NAME:
                if (!((String) newValue).matches("[A-Za-z ,.'-]*")) {
                    fld.setText((String) oldValue);
                }
                break;
            case ADDRESS:
                if (!((String) newValue).matches("[A-Za-z0-9 ,.'-]*")) {
                    fld.setText((String) oldValue);
                }
                break;
            case PHONE:
                if (!((String) newValue).matches("\\d*")) {
                    fld.setText((String) oldValue);
                }
                break;
            
            case STATE:
                if (!((String) newValue).matches("[A-Z]{0,2}")) {
                    fld.setText((String) oldValue);
                }
                break;    
                
            
            case ZIP:
                if (!((String) newValue).matches("\\d*") || ((String) newValue).length()>5) {
                    fld.setText((String) oldValue);
                }
                break;
            

        }

    }

}
