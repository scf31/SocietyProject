package ru.vsu.cs.java.model.characters.strategies;

import ru.vsu.cs.java.model.characters.*;
import ru.vsu.cs.java.model.characters.decisions.ChangeProfession;
import ru.vsu.cs.java.model.characters.decisions.Decision;
import ru.vsu.cs.java.model.characters.decisions.TraderHealing;
import ru.vsu.cs.java.model.enviroment.Habitat;
import ru.vsu.cs.java.model.enviroment.PersonalEnvironment;

/**
 * Created by serebryanskiysergei on 13-Oct-14.
 */
public class TraderStrategy implements IStrategy {
    public Decision takeDecision(IPersonToStrategy person, PersonalEnvironment personEnvir, Habitat settlement)
    {
        if (inDangerous(person))
        {
            return new TraderHealing();
        }
        if (person.getEquipment().howMoneyInCash() < 10) {
            ChangeProfession decision = new ChangeProfession();
            decision.newState = PersonState.LastActionCompleted;
            decision.newProfession = Profession.Peasant;
            return decision;
        }
        switch (person.getStatus())
        {
            case Ready:
                switch (person.getLastDecision().nextAction)
                {
                    case Free:
                        return Actions.free();
                }
                return person.getLastDecision();
            case LastActionCompleted:
                return Actions.trading(settlement, personEnvir);
            case Fighting:
                return Actions.attacking(person.getOpponent());
        }
        return Actions.free();

    }
    /// Проверка на опсность для жизни.
    /// В случае опасности стратегия переключается на безопасную.
    private boolean inDangerous(IPersonToStrategy person)
    {
        return person.getHealth() < 25;
    }
}




