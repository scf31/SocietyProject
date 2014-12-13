package ru.vsu.cs.java.model;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.enviroment.Habitat;
import ru.vsu.cs.java.model.prototypes.*;

/**
 * Created by serebryanskiysergei on 14-Oct-14.
 */
public class PersonFactory
{
    private int id;
    private WarriorPrototype _warriorPrototype;
    private TraderPrototype _traderPrototype;
    private RobberPrototype _robberPrototype;
    private PeasantPrototype _peasantPrototype;
    private CraftsmanPrototype _craftsmanPrototype;

    public Person getMan() throws CloneNotSupportedException
    {
        RandomContainer random = new RandomContainer();
        Person man = new Person(id);
        byte luck = (byte)random.next(1,6);
        if (luck == 1)
            man = _warriorPrototype.clone();
        if (luck == 2)
            man = _traderPrototype.clone();
        if (luck == 3)
            man = _robberPrototype.clone();
        if (luck == 4)
            man = _peasantPrototype.clone();
        if (luck == 5)
            man = _craftsmanPrototype.clone();
        man.setId(id++);
        return man;
    }
    public PersonFactory(Habitat settlement)
    {
        _warriorPrototype = new WarriorPrototype(settlement);
        _traderPrototype = new TraderPrototype(settlement);
        _robberPrototype = new RobberPrototype(settlement);
        _peasantPrototype = new PeasantPrototype(settlement);
        _craftsmanPrototype = new CraftsmanPrototype(settlement);
        id = 0;
    }
}