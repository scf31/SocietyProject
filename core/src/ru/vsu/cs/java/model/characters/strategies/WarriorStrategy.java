package ru.vsu.cs.java.model.characters.strategies;

import ru.vsu.cs.java.model.characters.*;
import ru.vsu.cs.java.model.characters.decisions.ChangeProfession;
import ru.vsu.cs.java.model.characters.decisions.Decision;
import ru.vsu.cs.java.model.characters.decisions.Run;
import ru.vsu.cs.java.model.enviroment.Habitat;
import ru.vsu.cs.java.model.enviroment.PersonalEnvironment;

/**
 * Created by serebryanskiysergei on 13-Oct-14.
 */
public class WarriorStrategy implements IStrategy {
    public Decision takeDecision(IPersonToStrategy person, PersonalEnvironment pEnvir, Habitat settlement)
    {
        if (person.getStatus() == PersonState.Died)
            return null;
        if (person.getEquipment().howMoneyInCash() < 10) {
            ChangeProfession decision = new ChangeProfession();
            decision.newState = PersonState.LastActionCompleted;
            decision.newProfession = Profession.Craftsman;
            return decision;
        }
        if (inDangerous(person) && person.getStatus()!=PersonState.Fighting)
        {
            person.changeStrategy(new SafeStrategy());
            person.setDecision(person.getStrategy().takeDecision((IPersonToStrategy) person, pEnvir, settlement));
            return person.getLastDecision();
        }
        switch (person.getStatus())
        {
            case LastActionCompleted:
                if (CheckEnviroment(person, pEnvir) == null)
                    return Actions.patrolling(settlement);
                else
                    return CheckEnviroment(person, pEnvir);
            case Moving:
                if (CheckEnviroment(person, pEnvir) == null)
                    return person.getLastDecision();
                else
                    return CheckEnviroment(person, pEnvir);
            case Ready:
                switch (person.getLastDecision().nextAction)
                {
                    case Attack:
                        return Actions.attacking(person.getOpponent());
                    case Free:
                        if (CheckEnviroment(person, pEnvir) != null)
                            return CheckEnviroment(person, pEnvir);
                        return Actions.free();
                }
                return Actions.free();
            case Run:
                if (CheckEnviroment(person, pEnvir) != null)
                    return CheckEnviroment(person, pEnvir);
                else
                    return Actions.free();
            case Fighting:
                return Actions.attacking(person.getOpponent());
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
    /// Проверка специально для война на присутствие разбойников в поле зрения.
    private Run CheckEnviroment(IPersonToStrategy person, PersonalEnvironment pEnvir)
    {
        for (IPersonToStrategy p : pEnvir.NearestCharacters)
        {
            if (p.getProfession()== Profession.Robber)
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


