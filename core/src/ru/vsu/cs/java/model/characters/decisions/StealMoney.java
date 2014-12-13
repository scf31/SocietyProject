package ru.vsu.cs.java.model.characters.decisions;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.PersonState;

/**
 * Created by serebryanskiysergei on 07-Oct-14.
 */
public class StealMoney extends Decision {
    public Person opponent;
    @Override
    public void apply(Person person)
    {
        person.addMoney(opponent.takeAllMoney());
        person.addExp(100);
        person.setStatus(PersonState.LastActionCompleted);
    }
}
