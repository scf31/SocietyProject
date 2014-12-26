package ru.vsu.cs.java.model.characters.strategies;

import ru.vsu.cs.java.model.characters.*;
import ru.vsu.cs.java.model.characters.decisions.BuyingMedicine;
import ru.vsu.cs.java.model.characters.decisions.Decision;
import ru.vsu.cs.java.model.characters.decisions.Run;
import ru.vsu.cs.java.model.enviroment.Habitat;
import ru.vsu.cs.java.model.enviroment.PersonalEnvironment;

import java.awt.*;

/**
 * Created by serebryanskiysergei on 13-Oct-14.
 */
public class SafeStrategy implements IStrategy {

    public Decision takeDecision(IPersonToStrategy person, PersonalEnvironment pEnvir, Habitat settlement)
    {
        if (person.getStatus() == PersonState.Died)
            return null;
        if (person.getHealth() > 25)
        {
            person.setDecision(Actions.free());
            switch (person.getProfession())
            {
                case Craftsman:
                    person.changeStrategy(new CraftsmanStrategy());
                    return person.getLastDecision();
                case Peasant:
                    person.changeStrategy(new PeasantStrategy());
                    return person.getLastDecision();
                case Robber:
                    person.changeStrategy(new RobberStrategy());
                    return person.getLastDecision();
                case Trader:
                    person.changeStrategy(new TraderStrategy());
                    return person.getLastDecision();
                case Warrior:
                    person.changeStrategy(new WarriorStrategy());
                    return person.getLastDecision();
            }
        }
        if (person.getStatus() == PersonState.Ready)
        {
            if (person.getLastDecision().nextAction == ActionType.BuyMedicine)
            {
                BuyingMedicine decision = new BuyingMedicine();
                decision.newState = PersonState.Ready;
                for (IPersonToStrategy p : pEnvir.NearestCharacters)
                {
                    if (p.getProfession() == Profession.Trader)
                        decision.opponent = (Person) p;
                }
                decision.nextAction = ActionType.Free;
                return decision;
            }
        }
        for (IPersonToStrategy p : pEnvir.NearestCharacters)
        {
            if (p.getProfession() == Profession.Trader)
            {
                Run decision = new Run();
                decision.newState = PersonState.Run;
                decision.nextAction = ActionType.BuyMedicine;
                decision.Destination = p.getLocation();
                return decision;
            }
        }
        if (person.getLocation() == settlement.findTrader().getLocation())
        {
            BuyingMedicine decision = new BuyingMedicine();
            decision.newState = PersonState.Ready;
            for (IPersonToStrategy p : pEnvir.NearestCharacters)
            {
                if (p.getProfession() == Profession.Trader)
                    decision.opponent = (Person)p;
            }
            decision.nextAction = ActionType.Free;
            return decision;
        }
        Run run = new Run();
        for (IPersonToStrategy p: pEnvir.NearestCharacters)
        {
            if (p.getProfession() == Profession.Trader)
            {
                run.Destination = p.getLocation();
                run.newState = PersonState.Run;
                run.nextAction = ActionType.BuyMedicine;
                return run;
            }
        }
        run.Destination = settlement.findTrader().getLocation();
        return run;
    }


}

