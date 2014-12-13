package ru.vsu.cs.java.model.characters.professions;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.Profession;
import ru.vsu.cs.java.model.characters.strategies.TraderStrategy;
import ru.vsu.cs.java.model.enviroment.Equipment;
import ru.vsu.cs.java.model.enviroment.Habitat;

import java.awt.*;

/**
 * Created by serebryanskiysergei on 11-Oct-14.
 */
public class TraderDecorator implements IProfessionDecorator {
    public void takeProfession(Person person,Habitat settlement)
    {
        person.setEqip(Equipment.StandartEquipment.Weapons.Hands, Equipment.StandartEquipment.Armors.NoneArmor, 100);
        person.setLocation(new Point((int)(Math.random() * ((settlement.getWight()) + 1)),(int)(Math.random() * ((settlement.getHeight()) + 1))));
        person.changeStrategy(new TraderStrategy());
        person.setReview(100);
        person.setSpeed(70);
        person.setProfession(Profession.Trader);
    }
}
