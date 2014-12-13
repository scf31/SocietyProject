package ru.vsu.cs.java.model;

import java.util.Random;

/**
 * Created by serebryanskiysergei on 13-Oct-14.
 */
public class RandomContainer {
    private Random random;
    public RandomContainer(){
        random = new Random();
    }
    public int next(){
        return random.nextInt();
    }
    public int next(int min ,int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
