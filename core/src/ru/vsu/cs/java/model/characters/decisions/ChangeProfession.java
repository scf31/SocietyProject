package ru.vsu.cs.java.model.characters.decisions;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.PersonState;
import ru.vsu.cs.java.model.characters.Profession;

/**
 * Created by serebryanskiysergei on 07-Oct-14.
 */
public class ChangeProfession extends Decision {

    public Profession newProfession;
    @Override
    public void apply(Person person)
    {
        person.setStatus(newState);
        person.setProfession(newProfession);
        person.setStatus(PersonState.LastActionCompleted);
        person.setDecision(null);
    }
}
