package tasker.view;

import tasker.model.Task;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class TaskStatusListener implements PropertyChangeListener {



    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("the task status has changed: "+evt.getPropertyName() +" "+ evt.getNewValue());
        System.out.println("event source: "+evt.getSource());
    }
}
