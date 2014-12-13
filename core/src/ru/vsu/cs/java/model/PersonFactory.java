package ru.vsu.cs.java.model;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.professions.*;
import ru.vsu.cs.java.model.enviroment.Habitat;

/**
 * Created by serebryanskiysergei on 14-Oct-14.
 */
public class PersonFactory
{
    private int id;
    private WarriorDecorator warriorDecorator;
    private TraderDecorator traderDecorator ;
    private RobberDecorator robberDecorator;
    private PeasantDecorator peasantDecorator;
    private CraftsmanDecorator craftsmanDecorator;
    private Habitat settlement;

    public Person getMan()
    {
        RandomContainer random = new RandomContainer();
        Person man = new Person(id);
        byte luck = (byte)random.next(1,6);
        if (luck == 1){
            warriorDecorator.takeProfession(man,settlement);
        }
        if (luck == 2){
            craftsmanDecorator.takeProfession(man,settlement);
        }
        if (luck == 3){
            peasantDecorator.takeProfession(man,settlement);
        }
        if (luck == 4){
            robberDecorator.takeProfession(man,settlement);
        }
        if (luck == 5){
            traderDecorator.takeProfession(man,settlement);
        }
        man.setId(id++);
        return man;
    }
    public PersonFactory(Habitat settlement)
    {
        warriorDecorator = new WarriorDecorator();
        traderDecorator = new TraderDecorator();
        robberDecorator = new RobberDecorator();
        peasantDecorator = new PeasantDecorator();
        craftsmanDecorator = new CraftsmanDecorator();
        id = 0;
        this.settlement=settlement;
    }
}