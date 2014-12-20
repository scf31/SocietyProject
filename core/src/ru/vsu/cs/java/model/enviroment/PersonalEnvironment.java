package ru.vsu.cs.java.model.enviroment;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.Kingdom;

import java.awt.*;
import java.util.*;

/**
 * Created by serebryanskiysergei on 05-Oct-14.
 */
public class PersonalEnvironment {

    public ArrayList<Person> NearestCharacters;

    public PersonalEnvironment(Person person, Kingdom world)
    {

        ArrayList<Person> allCharacters = new ArrayList<Person>(world.getCharacters().values());
        NearestCharacters = new ArrayList<Person>();
        for (Person man : allCharacters)
            if (Math.sqrt(Math.pow(man.getLocation().getX() - person.getLocation().getX(), 2) + Math.pow(man.getLocation().getY() - person.getLocation().getY(), 2)) <= (double)person.getReview())
                NearestCharacters.add(man);
    }

}
