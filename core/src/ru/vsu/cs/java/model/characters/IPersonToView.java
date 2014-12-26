package ru.vsu.cs.java.model.characters;

import ru.vsu.cs.java.model.enviroment.Equipment;

import java.awt.*;

/**
 * Created by VioLeY on 13.12.2014.
 */
public interface IPersonToView {

    public int getHealth();
    public int getLevel();
    public int getExperience();
    public int getReview();
    public int getFillingBag();
    public PersonState getStatus();
    public int getId();
    public Point getLocation();
    public Profession getProfession();
    public Equipment getEquipment();
    public Person getOpponent();
}
