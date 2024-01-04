package com.dynalektric.view.components;

import javax.swing.*;
import java.awt.*;

public class InputTextFieldWithLabel extends JPanel {
    private final JLabel label = new JLabel();
    private JTextField textField = new JTextField();
    private String labelName;
    public InputTextFieldWithLabel(){
        this.label.setText("");
        this.initTextField();
    }
    public InputTextFieldWithLabel(String labelName){
        this.label.setText(labelName);
        this.labelName = labelName;
        this.initTextField();
    }
    public InputTextFieldWithLabel(String labelName,String defaultValue){
       this.label.setText(labelName);
       this.labelName = labelName;
       this.textField.setText(defaultValue);
       this.initTextField();
    }
    public void setValueEntered(String value){
        this.textField.setText(value);
    }


    public String getValueEntered(){
        return this.textField.getText();
    }

    private void initTextField(){
        this.setLayout(new FlowLayout());
        this.add(this.label);
        this.add(this.textField);
        this.textField.setColumns(10);
        this.setMaximumSize(this.getPreferredSize());
    }
}
