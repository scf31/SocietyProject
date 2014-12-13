package ru.vsu.cs.java.model.characters.professions;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.Profession;
import ru.vsu.cs.java.model.characters.strategies.CraftsmanStrategy;
import ru.vsu.cs.java.model.enviroment.Equipment;
import ru.vsu.cs.java.model.enviroment.Habitat;

import java.awt.*;
import java.util.Random;

/**
 * Created by serebryanskiysergei on 11-Oct-14.
 */
public class CraftsmanDecorator implements IProfessionDecorator {
    public void takeProfession(Person person, Habitat settlement)
    {
        Random r = new Random();
        person.setSpeed(50);
        person.setEqip(Equipment.StandartEquipment.Weapons.Hands, Equipment.StandartEquipment.Armors.UsualArmor, 75);
        person.setLocation(new Point(r.nextInt(settlement.getWight()), r.nextInt(settlement.getHeight())));
        person.setReview(100);
        person.setStrategy(new CraftsmanStrategy());
        person.setProfession(Profession.Craftsman);
    }
}
