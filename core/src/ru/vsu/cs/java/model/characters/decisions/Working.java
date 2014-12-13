package ru.vsu.cs.java.model.characters.decisions;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.PersonState;

/**
 * Created by serebryanskiysergei on 07-Oct-14.
 */
public class Working extends Decision {
    @Override
    public void apply(Person person) {
        if (person.getFillingBag() >= 100)
        {
            person.addExp(100);
            person.setStatus(PersonState.Ready);
        }
        else
        {
            person.setStatus(PersonState.Working);
            person.addInBag((int) ((7 + (int)(Math.random() * ((15 - 7) + 1))) + (person.getLvlBonus() * 2)));
        }
    }
}
