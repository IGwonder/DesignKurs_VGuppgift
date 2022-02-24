package com.company;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SalesControl implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("******** System *******" + "\n"
                + "Sales control registered change in: " + evt.getPropertyName() + " : " + evt.getNewValue()
                + "\n"
        );
    }
}
