package com.dynalektric.view;

public class View{
    private static View view;
    private View(){

    }
    public static View getSingleton(){
        if(view == null){
            view = new View();
        }
        return view;
    }
}
