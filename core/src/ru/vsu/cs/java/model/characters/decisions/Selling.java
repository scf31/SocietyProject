package ru.vsu.cs.java.model.characters.decisions;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.PersonState;

/**
 * Created by serebryanskiysergei on 07-Oct-14.
 */
public class Selling extends Decision {
    public Person opponent;
    @Override
    public void apply(Person person) {
        int money = opponent.takeMoney(person.getFillingBag());
        person.addMoney(money);
        opponent.addExp(100);
        person.setFillingBag(0);
        person.setStatus(PersonState.Ready);
    }
}
