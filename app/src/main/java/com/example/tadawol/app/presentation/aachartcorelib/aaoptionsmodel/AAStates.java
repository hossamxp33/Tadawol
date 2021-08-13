package com.example.tadawol.app.presentation.aachartcorelib.aaoptionsmodel;

public class AAStates {

    public AAHover hover;
    public AASelect select;

    public AAStates hover(AAHover prop) {
        hover = prop;
        return this;
    }

    public AAStates select(AASelect prop) {
        select = prop;
        return this;
    }

}
