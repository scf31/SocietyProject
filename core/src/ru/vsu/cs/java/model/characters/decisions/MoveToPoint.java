package ru.vsu.cs.java.model.characters.decisions;

import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.PersonState;

import java.awt.*;

/**
 * Created by serebryanskiysergei on 07-Oct-14.
 */
public class MoveToPoint extends Decision {
    public Point destination;
    @Override
    public void apply(Person person)
    {
        if ((destination.x - person.getLocation().x) != 0 && (destination.y - person.getLocation().y)!=0)
        {
            double alpha =
                    Math.atan((destination.y - person.getLocation().y)/(destination.x - person.getLocation().x));
            double speedX = person.getSpeed()*0.05*Math.cos(alpha);
            double speedY = person.getSpeed()*0.05*Math.sin(alpha);
            if (Math.sqrt(Math.pow((person.getLocation().x - destination.x), 2) +
                    Math.pow((person.getLocation().y + destination.y), 2)) >
                    Math.sqrt(Math.pow((speedX), 2) + Math.pow((speedY), 2)))
            {
                if (person.getLocation().x < destination.x)
                {
                    person.setLocation(new Point(person.getLocation().y + (int) speedX,
                            person.getLocation().y + (int) speedY));
                    person.setStatus(PersonState.Moving);
                }
                else
                {
                    person.setLocation(new Point(person.getLocation().x - (int) speedX,
                            person.getLocation().y - (int) speedY));
                    person.setStatus(PersonState.Moving);
                }
            }
            else
            {
                person.setLocation(destination);
                person.setStatus(PersonState.Ready);
            }
        }
        else
        {
            person.setLocation(destination);
            person.setStatus(PersonState.Ready);
        }
    }
}
