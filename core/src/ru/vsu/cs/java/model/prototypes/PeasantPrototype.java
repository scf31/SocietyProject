package ru.vsu.cs.java.model.prototypes;

import ru.vsu.cs.java.model.characters.professions.PeasantDecorator;
import ru.vsu.cs.java.model.enviroment.Habitat;

/**
 * Created by serebryanskiysergei on 14-Oct-14.
 */
public class PeasantPrototype extends PersonPrototype {
    private static PeasantDecorator decorator = new PeasantDecorator();
    public PeasantPrototype(Habitat setllement){
        super(setllement, decorator);
    }
}
