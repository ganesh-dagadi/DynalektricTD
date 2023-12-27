package com.dynalektric.control;

import com.dynalektric.model.Model;

public class Control {
    Model model = Model.getSingleton();
    Calculations calculations = new Calculations();
    public Control(){

    }

}
