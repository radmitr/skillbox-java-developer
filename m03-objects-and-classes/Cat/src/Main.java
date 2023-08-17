// Task 3.1
// Task 3.2
// Task 3.3
// Task 3.4
// Task 3.5
// Task 3.6
// Task 3.7
// Task 3.9
public class Main {

    public static void main(String[] args) {
        Cat vaska = new Cat(3000.0, "Васька", Color.BLACK);
        Cat murka = new Cat(2550.0, "Мурка", Color.TORTOISESHELL);
        Cat pushok = new Cat(5000.0);

        Cat vaskaTwin = Cat.getTwin(vaska);

        pushok.setColor(Color.RED);
        pushok.setName("Пушок");
        murka.setColor(Color.AGOUTI);

        System.out.println("У нас " + Cat.getCount() + " кошки");
        System.out.println("У Васьки есть близнец-копия - " + vaskaTwin.getName()
                + " с весом " + vaskaTwin.getWeight()
                + " гр. и окрасом " + vaskaTwin.getColor());
        System.out.println(vaska.getName() + " весит " + vaska.getWeight() + " гр.");
        System.out.println(murka.getName() + " весит " + murka.getWeight() + " гр.");
        System.out.println(pushok.getName() + " весит " + pushok.getWeight() + " гр.");

        while (vaska.getWeight() >= vaska.getMinWeight()) {
            vaska.meow();
        }
        System.out.println(vaska.getName() + " теперь весит " + vaska.getWeight() +
                " гр. и " + vaska.getStatus());

        while (murka.getWeight() < murka.getMaxWeight()) {
            murka.drink(200.0);
            if (!murka.getStatus().equals("Exploded")) {
                murka.feed(500.0);
            }
        }
        System.out.println(murka.getName() + " съела " + murka.getFoodAmount() + " гр. еды");
        System.out.println(murka.getName() + " теперь весит " + murka.getWeight()
                + " гр. и " + murka.getStatus());

        pushok.feed(500.0);
        System.out.println(pushok.getName() + " теперь весит " + pushok.getWeight() + " гр.");
        System.out.println("Пушок у нас цвета - " + pushok.getColor());

        pushok.doShit();
        System.out.println("А теперь " + pushok.getName() + " весит " + pushok.getWeight() + " гр.");

        System.out.println("У Васьки есть близнец-копия - " + vaskaTwin.getName()
                + " с весом " + vaskaTwin.getWeight()
                + " гр. и окрасом " + vaskaTwin.getColor());
        vaskaTwin.feed(100.0);
        vaskaTwin.drink(200.0);
        System.out.println("Клон съел " + vaskaTwin.getFoodAmount() + " гр. еды");

        System.out.println("Итого " + Cat.getCount() + " кошка/кошек/кошки");
        while (!vaskaTwin.getStatus().equals("Dead")) {
            vaskaTwin.doShit();
        }

        vaskaTwin.drink(200.0);
        vaskaTwin.feed(500.0);
        vaskaTwin.meow();
        vaskaTwin.doShit();
        vaskaTwin.doShit(500.0);
        Cat vaskaTwin2 = Cat.getTwin(vaskaTwin);
        if (vaskaTwin2 != null) {
            System.out.println(vaskaTwin2.getWeight());
            System.out.println(vaskaTwin2.getName());
            System.out.println(vaskaTwin2.getStatus());
        }
    }
}
