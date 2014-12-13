package ru.vsu.cs.java.model.characters.strategies;

import ru.vsu.cs.java.model.characters.*;
import ru.vsu.cs.java.model.characters.decisions.Decision;
import ru.vsu.cs.java.model.characters.decisions.Run;
import ru.vsu.cs.java.model.enviroment.Habitat;
import ru.vsu.cs.java.model.enviroment.PersonalEnvironment;

import java.awt.*;

/**
 * Created by serebryanskiysergei on 13-Oct-14.
 */
public class RobberStrategy implements IStrategy
{
    public Decision takeDecision(IPersonToStrategy person,PersonalEnvironment personEnvir, Habitat settlement)
    {
        if (person.getStatus() == PersonState.Died)
            return null;
        switch (person.getStatus())
        {
            case LastActionCompleted:
                if (CheckEnviroment(person, personEnvir) == null)
                    return Actions.patroling(settlement);
                else
                    return CheckEnviroment(person, personEnvir);
            case Moving:
                if (CheckEnviroment(person, personEnvir) == null)
                    return person.getLastDecision();
                else
                    return CheckEnviroment(person, personEnvir);
            case Fighting:
            {
                if (person.getLocation()==person.getOpponent().getLocation())
                    return Actions.attacking(person.getOpponent());
                else
                    return CheckEnviroment(person, personEnvir);
            }
            case Ready:
                if (CheckEnviroment(person, personEnvir) == null)
                {
                    switch (person.getLastDecision().nextAction)
                    {
                        case Attack:
                            return Actions.attacking(person.getOpponent());
                        case StealMoney:
                            return Actions.stealMoney(person.getOpponent());
                        case Free:
                            return Actions.free();
                    }
                }
                else
                    return CheckEnviroment(person, personEnvir);
                return null;
            case Run:
                if (personEnvir.NearestCharacters.contains(person.getOpponent()))
                    return person.getLastDecision();
                else
                    return Actions.free();
        }
        return Actions.free();
    }
    /// <summary>
/// Проверка на опсность для жизни.
/// В случае опасности стратегия переключается на безопасную.
/// </summary>
/// <param name="person">Персонаж.</param>
/// <returns>В опасности или нет.</returns>
    private boolean inDangerous(IPersonToStrategy person)
    {
        if (person.getHealth() < 25)
            return true;
        else
            return false;
    }
    /// <summary>
/// Проверка специально для разбойника на присутствие воинов в поле зрения.
/// </summary>
/// <param name="person">Персонаж.</param>
/// <param name="pEnvir">Персонажи  области видимости.</param>
    private Run CheckEnviroment(IPersonToStrategy person, PersonalEnvironment pEnvir)
    {
        for (IPersonToStrategy p : pEnvir.NearestCharacters)
        {
            if (p.getProfession() == Profession.Warrior)
            {
                Run run = new Run();
                run.Destination= new Point(-p.getLocation().x,-p.getLocation().y);
                run.newState = PersonState.Run;
                run.nextAction = ActionType.Attack;
                return run;
            }
            if (p.getProfession() == Profession.Peasant &&
                    (person.getEquipment().equipCompare(p.getEquipment().getWeapon()) ||
                            person.getEquipment().equipCompare(p.getEquipment().getArmor()) ||
                            p.getEquipment().howMoneyInCash() > 0))
            {
                if (person.getLocation() == p.getLocation())
                {
                    return null;
                }
                else
                {
                    Run run = new Run();
                    run.Opponent = (Person) p;
                    run.newState = PersonState.Run;
                    run.nextAction = ActionType.StealMoney;
                    return run;
                }
            }
            if (p.getProfession() == Profession.Craftsman && (person.getEquipment().equipCompare(p.getEquipment().getWeapon()) ||
                    person.getEquipment().equipCompare(p.getEquipment().getArmor()) ||
                    p.getEquipment().howMoneyInCash() > 0))
            {
                Run run = new Run();
                run.Opponent = (Person) p;
                run.newState = PersonState.Run;
                run.nextAction = ActionType.Attack;
                return run;
            }
        }
        return null;
    }
}


