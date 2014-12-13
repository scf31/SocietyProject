package ru.vsu.cs.java.model.characters.professions;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.Profession;
import ru.vsu.cs.java.model.characters.strategies.WarriorStrategy;
import ru.vsu.cs.java.model.enviroment.Equipment;
import ru.vsu.cs.java.model.enviroment.Habitat;

import java.awt.*;
import java.util.Random;

/**
 * Created by serebryanskiysergei on 11-Oct-14.
 */
public class WarriorDecorator implements IProfessionDecorator {
    public void takeProfession(Person person, Habitat settlement)
    {
        Random r = new Random();
        person.setEqip(Equipment.StandartEquipment.Weapons.Sword, Equipment.StandartEquipment.Armors.MediumArmor, 30);
        person.setLocation(new Point(r.nextInt(settlement.getWight()), r.nextInt(settlement.getHeight())));
        person.changeStrategy(new WarriorStrategy());
        person.setReview(90);
        person.setSpeed(50);
        person.setProfession(Profession.Warrior);
    }
}
