package temp;
@FunctionalInterface
public interface WeaponBehavior {
    void useWeapon();
    int d = 12;  // 추상 메소드는 하나만 있다고 정의하는 FunctionalInterface
}
