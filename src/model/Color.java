package model;

public enum Color {
    black,white;
    Color next(){
        return this.equals(black)?white:black;
    }
}