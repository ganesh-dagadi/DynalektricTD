package com.dynalektric.view.components;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;


public class InputDropDown extends JPanel {

    private final String[] options;
    public JLabel label = new JLabel();

    public JComboBox<String> dropdown;
    public InputDropDown(String[] options,String labelName , String selectedValue){
        this.initDropDown(options);
        this.options = options;
        for(int i = 0;i<options.length;i++){
            if(Objects.equals(options[i], selectedValue)){
                this.dropdown.setSelectedIndex(i);
                break;
            }
        }
        this.label.setText(labelName);
    }
    public InputDropDown(String[] options,String labelName){
        this.label.setText(labelName);
        this.options = options;
        this.initDropDown(options);
    }

    public void setValueSelected(String selectedValue){
        for(int i = 0;i<options.length;i++){
            if(Objects.equals(options[i], selectedValue)) {
                this.dropdown.setSelectedIndex(i);
                break;
            }
        }
    }
    public String getValueSelected(){
        int selectedIndex = this.dropdown.getSelectedIndex();
        return this.options[selectedIndex];
    }
    private void initDropDown(String[] options){
        this.dropdown = new JComboBox<>(options);
        this.setLayout(new FlowLayout());
        this.add(label);
        this.add(dropdown);
        this.setMaximumSize(new Dimension(250 , 50));

    }


}
