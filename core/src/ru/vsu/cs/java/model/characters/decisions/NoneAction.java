package ru.vsu.cs.java.model.characters.decisions;

import ru.vsu.cs.java.model.characters.Person;

/**
 * Created by serebryanskiysergei on 07-Oct-14.
 */
public class NoneAction extends Decision {
    @Override
    public void apply(Person person)
    {
        person.setStatus(newState);
    }
}
