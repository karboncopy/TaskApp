package tasker.utilities;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;

public class ButtonBaseFactory {


    public ButtonBase getButtonBase(String type) {
        if (type.equalsIgnoreCase("BUTTON")) {
            return new Button("x");
        }else if(type.equalsIgnoreCase("CHECKBOX")){
            return new CheckBox();
        }
        return null;
    }


}
