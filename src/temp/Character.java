package temp;

public abstract class Character{
    protected int hp;
    protected int mp;
    // ...
    WeaponBehavior weapon;  // Association (Aggregation)
    public void info(){
        System.out.printf("체력은 %d 마력은 %d입니다 \n", hp ,mp);
    };

    public void setWeapon(WeaponBehavior weapon) {
        this.weapon = weapon;
    }
    public final void performWeapon(){ weapon.useWeapon(); }

}
