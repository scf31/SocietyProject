package ru.vsu.cs.java.model.prototypes;

import ru.vsu.cs.java.model.characters.professions.TraderDecorator;
import ru.vsu.cs.java.model.enviroment.Habitat;

/**
 * Created by serebryanskiysergei on 14-Oct-14.
 */
public class TraderPrototype extends PersonPrototype {
    private static TraderDecorator decorator = new TraderDecorator();
    public TraderPrototype(Habitat setllement){
        super(setllement, decorator);
    }
}
