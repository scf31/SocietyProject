package ru.vsu.cs.java.model.characters.professions;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.enviroment.Habitat;

/**
 * Created by serebryanskiysergei on 11-Oct-14.
 */
public interface IProfessionDecorator {
    void takeProfession(Person person, Habitat settlement);
}
