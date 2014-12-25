package ru.vsu.cs.java.model.characters.decisions;

import ru.vsu.cs.java.model.characters.ActionType;
import ru.vsu.cs.java.model.characters.Person;
import ru.vsu.cs.java.model.characters.PersonState;

import java.awt.*;

/**
 * Created by serebryanskiysergei on 07-Oct-14.
 */
public class Run extends Decision {

    public Person Opponent = null;
    public Point Destination;
    @Override
    public void apply(Person person)
    {
        if (Opponent == null)
        {
            double speedX, speedY;
            if ((Destination.x - person.getLocation().x) != 0 && (Destination.y - person.getLocation().y) != 0)
            {
                double alpha =
                        Math.atan((Destination.y - person.getLocation().y)/(Destination.x - person.getLocation().x));
                speedX = person.getHealth() > 35
                        ? person.getSpeed()*0.07*Math.cos(alpha)
                        : person.getSpeed()*0.02*Math.cos(alpha);
                speedY = person.getHealth() > 35
                        ? person.getSpeed()*0.07*Math.sin(alpha)
                        : person.getSpeed()*0.02*Math.sin(alpha);
            }
            else
            {
                if ((Destination.x - person.getLocation().x) == 0)
                {
                    speedY = person.getHealth() > 35 ? person.getSpeed()*0.15 : person.getSpeed()*0.1;
                    speedX = 0;
                }
                else
                {
                    speedX = person.getHealth() > 35 ? person.getSpeed()*0.15 : person.getSpeed()*0.1;
                    speedY = 0;
                }
            }
            if (
                    Math.sqrt(Math.pow((person.getLocation().x - Destination.x), 2) +
                            Math.pow((person.getLocation().y - Destination.y), 2)) >
                            Math.sqrt(Math.pow((speedX), 2) + Math.pow((speedY), 2)))
            {
                if (person.getLocation().x < Destination.x)
                {
                    person.setLocation(new Point(person.getLocation().x + (int) speedX,
                            person.getLocation().y + (int) speedY));
                    person.setStatus(PersonState.Run);
                }
                else
                {
                    person.setLocation(new Point(person.getLocation().x - (int) speedX,
                            person.getLocation().y - (int) speedY));
                    person.setStatus(PersonState.Run);
                }
            }
            else
            {
                person.setLocation(Destination);
                person.setStatus(PersonState.Ready);
            }
        }
        else // opponent != null
        {
            double speedX, speedY;
            if ((Opponent.getLocation().x - person.getLocation().x) != 0 &&
                    (Opponent.getLocation().y - person.getLocation().y) != 0)
            {
                double alpha =
                        Math.atan((Opponent.getLocation().y - person.getLocation().y)/
                                (Opponent.getLocation().x - person.getLocation().x));
                speedX = person.getHealth() > 35
                        ? person.getSpeed()*0.07*Math.cos(alpha)
                        : person.getSpeed()*0.02*Math.cos(alpha);
                speedY = person.getHealth() > 35
                        ? person.getSpeed()*0.07*Math.sin(alpha)
                        : person.getSpeed()*0.02*Math.sin(alpha);
            }
            else
            {
                if ((Opponent.getLocation().x - person.getLocation().x) == 0)
                {
                    speedY = person.getHealth() > 35 ? person.getSpeed() * 0.15 : person.getSpeed() * 0.1;
                    speedX = 0;
                }
                else
                {
                    speedX = person.getHealth() > 35 ? person.getSpeed() * 0.15 : person.getSpeed() * 0.1;
                    speedY = 0;
                }
            }
            if (
                    Math.sqrt(Math.pow((person.getLocation().x - Opponent.getLocation().x), 2) +
                            Math.pow((person.getLocation().y - Opponent.getLocation().y), 2)) >
                            Math.sqrt(Math.pow((speedX), 2) + Math.pow((speedY), 2)))
            {
                if (person.getLocation().x < Opponent.getLocation().x)
                {
                    person.setLocation(new Point(person.getLocation().x + (int) speedX,
                            person.getLocation().y + (int) speedY));
                    person.setStatus(PersonState.Run);
                }
                else
                {
                    person.setLocation(new Point(person.getLocation().x - (int) speedX,
                            person.getLocation().y - (int) speedY));
                    person.setStatus(PersonState.Run);
                }
            }
            else
            {
                person.setLocation(Opponent.getLocation());
                person.setStatus(PersonState.Ready);
                if (nextAction == ActionType.Attack){
                    Opponent.setStatus(PersonState.Fighting);
                    person.setStatus(PersonState.Fighting);
                    Opponent.setOpponent(person);
                    person.setOpponent(Opponent);
                }
                if (nextAction == ActionType.StealMoney)
                {
                    Opponent.setStatus(PersonState.Ready);
                    person.setStatus(PersonState.Ready);
                    person.setOpponent(Opponent);
                }
            }
        }
    }
}
