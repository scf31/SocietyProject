package ru.vsu.cs.java.model.characters.decisions;

import ru.vsu.cs.java.model.characters.ActionType;
import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.PersonState;

/**
 * Created by serebryanskiysergei on 07-Oct-14.
 */
public class TraderHealing extends Decision {
    public TraderHealing()
    {
        newState = PersonState.Ready;
        nextAction = ActionType.Free;
    }
    @Override
    public void apply(Person person)
    {
        person.changeHp(50);
        person.addMoney(-25);
        person.setStatus(PersonState.Ready);
    }
}
