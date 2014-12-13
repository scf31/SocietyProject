package ru.vsu.cs.java.model.characters.professions;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.Profession;
import ru.vsu.cs.java.model.characters.strategies.RobberStrategy;
import ru.vsu.cs.java.model.enviroment.Equipment;
import ru.vsu.cs.java.model.enviroment.Habitat;

import java.awt.*;
import java.util.Random;

/**
 * Created by serebryanskiysergei on 11-Oct-14.
 */
public class RobberDecorator implements IProfessionDecorator {
    public void takeProfession(Person person, Habitat settlement)
    {
        Random r = new Random() ;
        person.setEqip(Equipment.StandartEquipment.Weapons.Dagger, Equipment.StandartEquipment.Armors.UsualArmor, 30);
        person.setLocation(new Point((int)(Math.random() * ((settlement.getWight()/2) + 1)),(int)(Math.random() * ((settlement.getHeight()/2) + 1))));
        person.setReview(110);
        person.setSpeed(95);
        person.setStrategy (new RobberStrategy());
        person.setProfession (Profession.Robber);
    }
}
