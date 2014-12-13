package ru.vsu.cs.java.model.enviroment;

/**
 * Оружие персонажей.
 */
public class Weapon {
    private int damage;
    private int estatement;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        if(damage>0)
            this.damage = damage;
        else
            this.damage=0;
    }

    public int getEstatement() {
        return estatement;
    }

    public void setEstatement(int estatement) {
        if (estatement>0)
            this.estatement = estatement;
        else
            this.estatement=0;
    }

    public Weapon(int degree){
        switch (degree)
        {
            case 0:
                setDamage(5);
                setEstatement(100);
                break;
            case 1:
                setDamage(10);
                setEstatement(10);
                break;
            case 2:
                setDamage(15);
                setEstatement(15);
                break;
            case 3:
                setDamage(17);
                setEstatement(17);
                break;
        }
    }
    public Weapon(Weapon newWeapon)
    {
        setDamage(newWeapon.getDamage());
        setEstatement(newWeapon.getEstatement());
    }
    /**
     * Лучше ли передаваемое оружие?
    **/
    public boolean Compare(Weapon w2)
    {
        if (this.getDamage() < w2.getDamage())
            return true;
        else
        {
            if (this.getDamage() == w2.getDamage() && this.getEstatement() < w2.getEstatement())
                return true;
            else
                return false;
        }
    }
}
