package ru.vsu.cs.java.model;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.PersonState;
import ru.vsu.cs.java.model.characters.decisions.Decision;
import ru.vsu.cs.java.model.characters.IPersonToView;
import ru.vsu.cs.java.model.enviroment.PersonalEnvironment;
import java.awt.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by VioLeY on 13.12.2014.
 */
public class Engine  {

    private Kingdom kingdom;

    public Engine(int peopleCount, int widthRatio,int heightRatio){
        kingdom = new Kingdom(peopleCount,widthRatio,heightRatio);
    }
    public void step(){
        Hashtable<Integer,Person> hashtable = kingdom.getCharacters();
        ArrayList<Integer> idToDelelte = new ArrayList<Integer>();
        for (Person man : hashtable.values()) {
            if (man.getStatus() != PersonState.Died && man.getHealth() > 0) {
                Decision newDecision = man.getStrategy().takeDecision(man, new PersonalEnvironment(man, kingdom), kingdom.getHabitat());
                man.setDecision(newDecision);
                if (newDecision != null)
                    newDecision.apply(man);
                if (man.getStatus()==PersonState.Died)
                    idToDelelte.add(man.getId());
            }
        }
        for (Integer id : idToDelelte) {
            hashtable.remove(id);
        }
    }
    public ArrayList<IPersonToView> getCharactersData(){
        return new ArrayList<IPersonToView>(kingdom.getCharacters().values());
    }
    public Hashtable<String,Rectangle> getMapData(){
        Hashtable<String,Rectangle> result = new Hashtable<String,Rectangle>();
        result.put("castle",kingdom.getHabitat().Castle);
        result.put("forest",kingdom.getHabitat().Forest);
        result.put("farm",kingdom.getHabitat().Farm);
        result.put("crafthouse", kingdom.getHabitat().CraftHouse);
        return result;
    }

}
