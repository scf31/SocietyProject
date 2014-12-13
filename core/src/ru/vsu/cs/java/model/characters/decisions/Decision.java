package ru.vsu.cs.java.model.characters.decisions;

import ru.vsu.cs.java.model.characters.ActionType;
import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.PersonState;

/**
 * Решение персонажа о следующем действии.
 */
public abstract class Decision {
    public PersonState newState;
    public ActionType nextAction;
    public abstract void apply(Person person);
}
