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
        NearestCharacters = new ArrayList<Person>();

        Enumeration<Point> enumKey = world.getHabitat().getLocationsOfSettlers().keys();
        while(enumKey.hasMoreElements()) {
            Point key = enumKey.nextElement();
            Integer val =  world.getHabitat().getLocationsOfSettlers().get(key);
            if (Math.sqrt(Math.pow(key.x - person.getLocation().x, 2) + Math.pow(key.y - person.getLocation().y, 2)) <= person.getReview())
                NearestCharacters.add(world.getCharacter(val));
        }

    }

}
