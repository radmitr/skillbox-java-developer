
public class Cat {

    private static int count = 0;
    private static final int EYES_COUNT = 2;
    private static final double MIN_WEIGHT = 1000.0;
    private static final double MAX_WEIGHT = 9000.0;

    private double originWeight;
    private double weight;
    private Color color;
    private String name;
    private double foodAmount;

    public Cat(double weight, String name, Color color) {
        if (weight > MIN_WEIGHT && weight < MAX_WEIGHT){
            this.weight = weight;
            originWeight = weight;
            this.name = name;
            this.color = color;
            count++;
            System.out.println("Плюс кошка");
        } else {
            System.out.println("Вы пытаетесь создать неправильную кошку");
        }
    }

    public Cat(double weight) {
        this(weight, "", null);
    }

    public Cat(double weight, String name) {
        this(weight, name, null);
    }

    public Cat() {
        this(1500.0 + 3000.0 * Math.random());
    }

    boolean isAlive() {
        return weight >= MIN_WEIGHT && weight <= MAX_WEIGHT;
    }

    boolean isAliveStatus(Cat cat){
        return (cat.getStatus().equals("Sleeping")) || (cat.getStatus().equals("Playing"));
    }

    public static int getCount() {
        return count;
    }

    public double getOriginWeight() {
        return originWeight;
    }

    public double getMinWeight() {
        return MIN_WEIGHT;
    }

    public double getMaxWeight() {
        return MAX_WEIGHT;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Cat getTwin(Cat cat) {
        System.out.println(cat.getStatus());
        if ((cat.getWeight() > MIN_WEIGHT) && (cat.getWeight() < MAX_WEIGHT)) { //клонированию подлежат только живые
            return new Cat(cat.getWeight(), cat.getName(), cat.getColor());
        } else {
            return null;
        }
    }

    public void meow() {
        if (isAlive()) {
            weight = weight - 1;
            System.out.println("Meow");
            if (weight < MIN_WEIGHT) {
                count--;
                System.out.println("Минус кошка");
            }
        } else System.out.println("Эта кошка уже отмяукала(((");
    }

    public void feed(Double amount) {
        if (isAlive()) {
            if (amount > 0) {
                weight = weight + amount;
                foodAmount = foodAmount + amount;
                if (weight > MAX_WEIGHT) {
                    count--;
                    System.out.println("Минус кошка");
                }
            }
        } else {
            System.out.println("Ошибка! Кошка мертва, не забудьте избавиться от трупа...");
        }
    }

    public void drink(Double amount) {
        if (isAlive()) {
            if (amount > 0) {
                weight = weight + amount;
                if (weight > MAX_WEIGHT) {
                    count--;
                    System.out.println("Минус кошка");
                }
            }
        } else {
            System.out.println("Ошибка! Кошка мертва, не забудьте избавиться от трупа...");
        }
    }

    public Double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        if (weight < MIN_WEIGHT) {
            return "Dead";
        } else if (weight > MAX_WEIGHT) {
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }

    public double getFoodAmount() {
        return foodAmount;
    }

    public void doShit(double kickWeight) {
        if (isAlive()) {
            weight = weight - kickWeight;
            System.out.println("Мам, я покакала!");
            if (weight < MIN_WEIGHT) {
                count--;
                System.out.println("Минус кошка");
            }
        } else {
            System.out.println("Какули закончились вместе с кошкой(((");
        }
    }

    public void doShit(){
        double kickWeight = getFoodAmount() * 0.3;
        this.doShit(kickWeight);
    }
}