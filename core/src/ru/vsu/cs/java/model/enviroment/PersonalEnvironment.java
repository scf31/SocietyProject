package ru.vsu.cs.java.model.enviroment;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.Kingdom;

import java.awt.*;
import java.util.*;

/**
 * Created by serebryanskiysergei on 05-Oct-14.
 */
public class PersonalEnvironment {

    public Collection<Person> NearestCharacters;

    public PersonalEnvironment(Person person, Kingdom world)
    {
        NearestCharacters = new ArrayList<Person>();
        NearestCharacters = world.getCharacters().values();
        for (Person man : NearestCharacters)
            if (Math.sqrt(Math.pow(man.getLocation().x - person.getLocation().x, 2) + Math.pow(man.getLocation().y - person.getLocation().y, 2)) <= person.getReview())
                NearestCharacters.add(man);
    }

}
