package temp;

public class Main {
    public static void main(String[] args) {
        Barbarian ah = new Barbarian();
        Orb fireorb = new Orb();
        Axe berserkeraxe = new Axe();
        Bow windhelm = new Bow();
        ah.setWeapon(berserkeraxe);
        ah.performWeapon();
        ah.setWeapon(windhelm);
        ah.performWeapon();
        ah.setWeapon(fireorb);
        ah.performWeapon();
        ah.info();
        ah.setWeapon(() -> System.out.println("신 오브로 아이스볼 발사"));  // lambda코드로 활용하기
        ah.performWeapon();


    }
}