package ru.vsu.cs.java.model.enviroment;

/**
 * Снаряжение персонажа.
 */
public class Equipment {

    private Weapon personalWeapon;
    private Armor personalArmor;
    private Cash personalCash;

    public Weapon getPersonalWeapon() {
        return personalWeapon;
    }

    public void setPersonalWeapon(Weapon personalWeapon) {
        this.personalWeapon = personalWeapon;
    }

    public Armor getPersonalArmor() {
        return personalArmor;
    }

    public void setPersonalArmor(Armor personalArmor) {
        this.personalArmor = personalArmor;
    }

    public Cash getPersonalCash() {
        return personalCash;
    }

    public void setPersonalCash(Cash personalCash) {
        this.personalCash = personalCash;
    }
    public Equipment(Weapon w, Armor a,int money)
    {
        setPersonalWeapon(w);
        setPersonalArmor(a);
        setPersonalCash(new Cash(money));
    }
    public void сhangeWeapon(Weapon newWp)
    {
        setPersonalWeapon(newWp);
    }
    public void сhangeArmor(Armor newAr)
    {
        setPersonalArmor(newAr);
    }
    public void сhangeCash(int n)
    {
        if (n > 0)
            getPersonalCash().add(n);
        else
            getPersonalCash().get(-n);
    }
    public void newCash(int money)
    {
        setPersonalCash(new Cash(money));
    }
    public Weapon getWeapon()
    {
        return getPersonalWeapon();
    }
    public Armor getArmor()
    {
        return getPersonalArmor();
    }
    public int howMoneyInCash()
    {
        return getPersonalCash().quantity();
    }
    public int getDamage()
    {
        return getPersonalWeapon().getDamage();
    }
    public int getStandUp()
    {
        return getPersonalArmor().getStandUp();
    }
    public void weaponUsed()
    {
        getPersonalArmor().setEstatement(getPersonalArmor().getEstatement()-1);
    }
    public void armorUsed()
    {
        getPersonalArmor().setEstatement(getPersonalArmor().getEstatement()-1);
    }
    public boolean equipCompare(Weapon weaponToCompare)
    {
        return getPersonalWeapon().Compare(weaponToCompare);
    }
    public boolean equipCompare(Armor armorToCompare)
    {
        return getPersonalArmor().Compare(armorToCompare);
    }
    public static class StandartEquipment {

        public static class Weapons{
            public static Weapon Hands = new Weapon(0);
            public static Weapon Dagger = new Weapon(1);
            public static Weapon Sword = new Weapon(2);
            public static Weapon Axe = new Weapon(3);
        }
        public static class Armors{
            public static Armor NoneArmor = new Armor(0);
            public static Armor UsualArmor = new Armor(1);
            public static Armor MediumArmor = new Armor(2);
            public static Armor HardArmor = new Armor(3);
        }
    }
}
