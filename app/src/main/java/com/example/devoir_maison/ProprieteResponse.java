package com.example.devoir_maison;

import java.util.ArrayList;
import java.util.List;


public class ProprieteResponse {
    public boolean success ;
    public ArrayList<Propriete> response ;
    public ProprieteResponse(){

    }
    public ArrayList<Propriete> getResponse(){

        return response;

    }
}