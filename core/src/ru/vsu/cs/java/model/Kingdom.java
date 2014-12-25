package ru.vsu.cs.java.model;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.professions.*;
import ru.vsu.cs.java.model.enviroment.Habitat;

import java.util.Hashtable;

/**
 * Государство . Весь мир собственно.
 */
public class Kingdom
{
    private Habitat _settlement;
    private Hashtable<Integer, Person> _settlers = new Hashtable<Integer, Person>();

    public Kingdom(int countOfSettlers, int drawAreaWidht, int drawAreaHeight)
    {

        _settlement = new Habitat(drawAreaWidht, drawAreaHeight);
        PersonFactory factory = new PersonFactory(_settlement);
        for (int i = 0; i <countOfSettlers; i++)
            _settlers.put(i,factory.getMan());
    }
    public Kingdom (int drawAreaWidht, int drawAreaHeight){
        Person man;
        _settlement = new Habitat(drawAreaWidht, drawAreaHeight);
        man = new Person(1);
        WarriorDecorator warriorDecorator = new WarriorDecorator();
        warriorDecorator.takeProfession(man,_settlement);
        _settlers.put(0,man);
        man = new Person(2);
        CraftsmanDecorator crd = new CraftsmanDecorator();        // !!!!!!
        crd.takeProfession(man,_settlement);
        _settlers.put(1,man);
        man = new Person(3);
        RobberDecorator rbd = new RobberDecorator();
        rbd.takeProfession(man,_settlement);
        _settlers.put(2,man);
        man = new Person(4);
        TraderDecorator trd = new TraderDecorator();
        trd.takeProfession(man,_settlement);
        _settlers.put(3,man);
        man = new Person(5);
        PeasantDecorator ped = new PeasantDecorator();
        ped.takeProfession(man,_settlement);
        _settlers.put(4,man);
    }
    public Hashtable<Integer,Person> getCharacters(){
        return _settlers;
    }
    public Habitat getHabitat()
    {
        return _settlement;
    }
}


