package ru.vsu.cs.java.model.prototypes;

import ru.vsu.cs.java.model.characters.professions.RobberDecorator;
import ru.vsu.cs.java.model.enviroment.Habitat;

/**
 * Created by serebryanskiysergei on 14-Oct-14.
 */
public class RobberPrototype extends PersonPrototype{
    private static RobberDecorator decorator = new RobberDecorator();
    public RobberPrototype(Habitat setllement){
        super(setllement, decorator);
    }
}
