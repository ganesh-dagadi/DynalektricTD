package com.dynalektric.view.components;

import javax.swing.*;
import java.awt.*;

public class InputSpinner extends JPanel {
    private final JLabel label = new JLabel();
    private final JSpinner spinner = new JSpinner(new SpinnerNumberModel(1.4 , 0.6 , 1.75 , 0.025));
    private String labelName;
    public InputSpinner(){
        this.label.setText("");
        this.initSpinner();
    }
    public InputSpinner(String labelName){
        this.label.setText(labelName);
        this.labelName = labelName;
        this.initSpinner();
    }
    public InputSpinner(String labelName,Double defaultValue){
        this.label.setText(labelName);
        this.labelName = labelName;
        this.spinner.setValue(defaultValue);
        this.initSpinner();
    }
    public void setValueEntered(Double value){
        this.spinner.setValue(value);
    }


    public Double getValueEntered(){
        return (Double) this.spinner.getValue();
    }

    private void initSpinner(){
        this.setLayout(new FlowLayout());
        this.add(this.label);
        this.add(this.spinner);
        this.setSize(this.getPreferredSize());
    }
}
