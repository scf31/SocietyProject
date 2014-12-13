package ru.vsu.cs.java.model.characters.professions;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.Profession;
import ru.vsu.cs.java.model.characters.strategies.PeasantStrategy;
import ru.vsu.cs.java.model.enviroment.Equipment;
import ru.vsu.cs.java.model.enviroment.Habitat;

import java.awt.*;
import java.util.Random;

/**
 * Created by serebryanskiysergei on 11-Oct-14.
 */
public class PeasantDecorator implements IProfessionDecorator {
    public void takeProfession(Person person, Habitat settlement)
    {
        Random r = new Random();
        person.setEqip(Equipment.StandartEquipment.Weapons.Hands, Equipment.StandartEquipment.Armors.NoneArmor, 50);
        person.setLocation(new Point(r.nextInt(settlement.getWight()), r.nextInt(settlement.getHeight())));
        person.setReview(100);
        person.setSpeed(70);
        person.setStrategy(new PeasantStrategy());
        person.setProfession(Profession.Peasant);
    }
}
