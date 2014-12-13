package ru.vsu.cs.java.model.characters;

import ru.vsu.cs.java.model.characters.decisions.Decision;
import ru.vsu.cs.java.model.characters.strategies.IStrategy;
import ru.vsu.cs.java.model.enviroment.Equipment;

import java.awt.*;

/**
 * Интерфейс для работы стратегии с персонажем
 */
public interface IPersonToStrategy {
    Person getOpponent();
    void setOpponent(Person op);
    int getHealth();
    int getLvlBonus();
    int getReview();
    Equipment getEquipment();
    PersonState getStatus();
    Decision getLastDecision();
    int getSpeed();
    Point getLocation();
    int getId();
    IStrategy getStrategy();
    Profession getProfession();
    void setDecision(Decision newDecision);
    void changeStrategy(IStrategy newStrategy);

}

