package ru.vsu.cs.java.model.prototypes;

import ru.vsu.cs.java.model.characters.professions.CraftsmanDecorator;
import ru.vsu.cs.java.model.enviroment.Habitat;

/**
 * Created by serebryanskiysergei on 14-Oct-14.
 */
public class CraftsmanPrototype  extends PersonPrototype{
    private static CraftsmanDecorator decorator = new CraftsmanDecorator();

    public CraftsmanPrototype(Habitat setllement){
        super(setllement, decorator);
    }
}
