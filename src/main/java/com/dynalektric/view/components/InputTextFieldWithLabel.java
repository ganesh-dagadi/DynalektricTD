package com.dynalektric.view.components;

import javax.swing.*;
import java.awt.*;

public class InputTextFieldWithLabel extends JPanel {
    private final JLabel label = new JLabel();
    private final JTextField textField = new JTextField();
    public InputTextFieldWithLabel(){
        this.label.setText("");
        this.initTextField();
    }
    public InputTextFieldWithLabel(String labelName){
        this.label.setText(labelName);
        this.initTextField();
    }
    public InputTextFieldWithLabel(String labelName,Integer defaultValue){
       this.label.setText(labelName);
       this.textField.setText(defaultValue.toString());
       this.initTextField();
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
