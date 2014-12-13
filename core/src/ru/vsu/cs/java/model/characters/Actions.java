package ru.vsu.cs.java.model.characters;

import ru.vsu.cs.java.model.characters.decisions.*;
import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.enviroment.Habitat;
import ru.vsu.cs.java.model.enviroment.PersonalEnvironment;
import ru.vsu.cs.java.model.RandomContainer;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by serebryanskiysergei on 11-Oct-14.
 */
public class Actions {
    static RandomContainer random = new RandomContainer();
    public static Attack attacking(IPersonToStrategy opponent)
    {
        Attack decision = new Attack();
        decision.opponent = (Person) opponent;
        decision.newState = PersonState.Fighting;
        decision.nextAction = ActionType.Free;
        return decision;
    }
    public static BuyingInstruments buyingInstruments(Habitat settlement, PersonalEnvironment pEnvir)
    {
        BuyingInstruments decision = new BuyingInstruments();
        decision.newState = PersonState.Ready;
        decision.nextAction = ActionType.MoveToCrafthouse;
        Iterator<Person> iter = pEnvir.NearestCharacters.iterator();
        while(iter.hasNext()){
            Person item = iter.next();
            if(item.getProfession()==Profession.Trader)
                decision.opponent=item;
        }
        return decision;
    }
    public static BuyingMedicine buyMedicine(IPersonToStrategy opponent)
    {
        BuyingMedicine decsion = new BuyingMedicine();
        decsion.newState = PersonState.LastActionCompleted;
        decsion.opponent = (Person) opponent;
        decsion.nextAction = ActionType.Free;
        return decsion;
    }
    public static Decision free()
    {
        NoneAction decision = new NoneAction(); decision.newState = PersonState.LastActionCompleted;
        decision.nextAction = ActionType.Free;
        return decision;
    }
    public static MoveToPoint patrolling(Habitat settlement)
    {
        MoveToPoint decision = new MoveToPoint();
        decision.destination = new Point((int)(Math.random() * ((settlement.getWight()) + 1)),(int)(Math.random() * ((settlement.getHeight()) + 1)));
        decision.newState = PersonState.Moving;
        decision.nextAction = ActionType.Free;
        return decision;
    }
    public static StealMoney stealMoney(IPersonToStrategy opponent)
    {
        StealMoney decision = new StealMoney();
        decision.opponent = (Person) opponent;
        decision.newState = PersonState.Ready;
        decision.nextAction = ActionType.Free;
        return decision;
    }
    public static Selling sell(Habitat settlement, PersonalEnvironment pEnvir)
    {
        Selling decision = new  Selling ();
        Iterator<Person> iter = pEnvir.NearestCharacters.iterator();
        while(iter.hasNext()){
            Person item = iter.next();
            if(item.getProfession()==Profession.Trader)
                decision.opponent=item;
        }
        decision.newState = PersonState.Ready;
        decision.nextAction = ActionType.Free;
        return decision;
    }
    public static Trade trading(Habitat settlement, PersonalEnvironment pEnvir)
    {
        Trade decision = new Trade();
        decision.newState = PersonState.Ready;
        decision.nextAction = ActionType.Free;
        return decision;
    }
    public static MoveToPoint goingToFarm(Habitat settlement,PersonalEnvironment pEnvir)
    {

        MoveToPoint move = new MoveToPoint();
        move.newState = PersonState.Moving;
        move.nextAction = ActionType.Work;
        move.destination = new Point(random.next(settlement.Farm.x, settlement.Farm.x + settlement.Farm.width),
                random.next(settlement.Farm.y, settlement.Farm.y + settlement.Farm.height));
        return move;
    }


    public static Working work(Habitat settlement,PersonalEnvironment pEnvir)
    {
        Working decision = new Working();
        decision.newState = PersonState.Working;
        decision.nextAction = ActionType.MoveToTrader;
        return decision;
    }

    public static MoveToPoint goingToTrader(Habitat settlement,PersonalEnvironment pEnvir)
    {
        MoveToPoint decision = new MoveToPoint();
        decision.destination = settlement.findTrader();
        decision.nextAction = ActionType.Sell;
        decision.newState = PersonState.Moving;
        return decision;
    }

    public static MoveToPoint goingForInstruments(Habitat settlement, PersonalEnvironment pEnvir)
    {
        MoveToPoint decision = new MoveToPoint();
        decision.destination = settlement.findTrader();
        decision.nextAction = ActionType.BuyInstruments;
        decision.newState = PersonState.Moving;
        return decision;
    }
    public static MoveToPoint goingToCrafthouse(Habitat settlement, PersonalEnvironment pEnvir)
    {
        MoveToPoint decision = new MoveToPoint();
        decision.newState = PersonState.Moving;
        decision.nextAction = ActionType.Craft;
        decision.destination =new Point(random.next(settlement.CraftHouse.x,
                settlement.CraftHouse.x + settlement.CraftHouse.width),random.next(settlement.CraftHouse.y,
                settlement.CraftHouse.y + settlement.CraftHouse.height));
        return decision;
    }
    public static Working crafting(Habitat settlement, PersonalEnvironment pEnvir)
    {
        Working decision = new Working ();
        decision.newState = PersonState.Working;
        decision.nextAction = ActionType.MoveToTrader;
        return decision;
    }
}
