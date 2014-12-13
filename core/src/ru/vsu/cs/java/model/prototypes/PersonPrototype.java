package ru.vsu.cs.java.model.prototypes;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.professions.IProfessionDecorator;
import ru.vsu.cs.java.model.enviroment.Habitat;

/**
 * Created by serebryanskiysergei on 14-Oct-14.
 */
public abstract class PersonPrototype {

    protected Person prototype;
    private IProfessionDecorator decorator;
    private Habitat settlemtnt;
    protected static int id = 0;

    protected PersonPrototype(Habitat setllement, IProfessionDecorator decorator)
    {
        this.prototype = new Person(0);
        this.decorator = decorator;
        this.settlemtnt = setllement;
    }

    public Person clone() throws CloneNotSupportedException
    {
        Person newMan = this.prototype.clone();
        decorator.takeProfession(newMan, settlemtnt);
        return newMan;
    }
}

