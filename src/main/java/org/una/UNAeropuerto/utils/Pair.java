/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.UNAeropuerto.utils;

/**
 *
 * @author roberth :)
 * @param <type1> Tipo de dato 1
 * @param <type2> Tipo de dato 2
 */
public class Pair<type1, type2> {

    private type1 key;
    private type2 value;

    public Pair(type1 object1, type2 object2) {
        this.key = object1;
        this.value = object2;
    }

    public type1 getKey() {
        return key;
    }

    public void setKey(type1 object1) {
        this.key = object1;
    }

    public type2 getValue() {
        return value;
    }

    public void setValue(type2 object2) {
        this.value = object2;
    }

}
