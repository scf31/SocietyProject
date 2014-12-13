package ru.vsu.cs.java.model.characters.strategies;

import ru.vsu.cs.java.model.characters.decisions.Decision;
import ru.vsu.cs.java.model.characters.IPersonToStrategy;
import ru.vsu.cs.java.model.enviroment.Habitat;
import ru.vsu.cs.java.model.enviroment.PersonalEnvironment;

/**
 * Стратегия персонажа.
 * Принимает решение что делать персонажу в следующий ход.
 */
public interface IStrategy {
    Decision takeDecision(IPersonToStrategy person,PersonalEnvironment personEnvir, Habitat settlement);
}
