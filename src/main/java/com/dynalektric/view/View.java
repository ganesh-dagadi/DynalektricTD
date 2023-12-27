package com.dynalektric.view;

import com.dynalektric.model.Model;

public class View{
    private static View view;
    Model model = Model.getSingleton();
    private View(){

    }
    public static View getSingleton(){
        if(view == null){
            view = new View();
        }
        return view;
    }

    public void initView(){

    }

    private void initializeUI(){

    }
}
