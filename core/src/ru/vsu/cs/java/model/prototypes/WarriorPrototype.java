package ru.vsu.cs.java.model.prototypes;

import ru.vsu.cs.java.model.characters.professions.WarriorDecorator;
import ru.vsu.cs.java.model.enviroment.Habitat;

/**
 * Created by serebryanskiysergei on 14-Oct-14.
 */
public class WarriorPrototype extends PersonPrototype {
    private static WarriorDecorator decorator = new WarriorDecorator();
    public WarriorPrototype(Habitat settlement)
    {
        super(settlement, decorator);
    }
}
