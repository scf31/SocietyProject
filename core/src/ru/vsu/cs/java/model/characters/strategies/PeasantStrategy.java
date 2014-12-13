package ru.vsu.cs.java.model.characters.strategies;

import ru.vsu.cs.java.model.characters.*;
import ru.vsu.cs.java.model.characters.decisions.ChangeProfession;
import ru.vsu.cs.java.model.characters.decisions.Decision;
import ru.vsu.cs.java.model.characters.decisions.Run;
import ru.vsu.cs.java.model.enviroment.Habitat;
import ru.vsu.cs.java.model.enviroment.PersonalEnvironment;

import java.awt.*;

/**
 * Created by serebryanskiysergei on 13-Oct-14.
 */
public class PeasantStrategy implements IStrategy {

    public Decision takeDecision(IPersonToStrategy person, PersonalEnvironment personEnvir, Habitat settlement)
    {
        if (person.getStatus() == PersonState.Died)
            return null;
        if (person.getEquipment().howMoneyInCash() < 10)
        {
            ChangeProfession decision = new ChangeProfession();
            decision.newProfession = Profession.Robber;
            decision.newState = PersonState.LastActionCompleted;
            return decision;
        }
        if (inDangerous(person))
        {
            person.changeStrategy(new SafeStrategy());
            return person.getStrategy().takeDecision(person, personEnvir, settlement);
        }
        switch (person.getStatus())
        {
            case LastActionCompleted:
                if (CheckEnviroment(person, personEnvir, settlement) == null)
                    return Actions.goingToFarm(settlement, personEnvir);
                else
                    return CheckEnviroment(person, personEnvir, settlement);
            case Ready:
                if (CheckEnviroment(person, personEnvir, settlement) == null)
                    switch (person.getLastDecision().nextAction)
                    {
                        case Work:
                            return Actions.work(settlement, personEnvir);
                        case MoveToTrader:
                            return Actions.goingToTrader(settlement, personEnvir);
                        case Sell:
                            return Actions.sell(settlement, personEnvir);
                        case Free:
                            return Actions.free();
                    }
                else
                    return CheckEnviroment(person, personEnvir, settlement);
                return Actions.free();
            case Working:
                if (CheckEnviroment(person, personEnvir, settlement) == null)
                    return person.getLastDecision();
                else
                    return CheckEnviroment(person, personEnvir, settlement);
            case Moving:
                if (CheckEnviroment(person, personEnvir, settlement) == null)
                    return person.getLastDecision();
                else
                    return CheckEnviroment(person, personEnvir, settlement);
            case Run:
                return person.getLastDecision();
            case Fighting:
                return Actions.attacking(person.getOpponent());
        }
        return Actions.free();
    }

    private boolean inDangerous(IPersonToStrategy person)
    {
        if (person.getHealth() < 25)
            return true;
        else
            return false;
    }
    /// Проверка специально для крестьянина на присутствие разбойников в поле зрения.
    private Run CheckEnviroment(IPersonToStrategy person, PersonalEnvironment pEnvir, Habitat setllement)
    {
        for(IPersonToStrategy p : pEnvir.NearestCharacters)
        {
            if (p.getProfession() == Profession.Robber)
            {
                Run run = new Run();
                run.Destination = new Point(setllement.Castle.getLocation().x + setllement.Castle.width/2,
                        setllement.Castle.y + setllement.Castle.height/2);
                for (Person man : pEnvir.NearestCharacters)
                {
                    if (man.getProfession() == Profession.Warrior)
                    {
                        run.Opponent = man;
                        break;
                    }
                }
                run.newState = PersonState.Run;
                run.nextAction = ActionType.Free;
                return run;
            }
        }
        return null;
    }
}


