package datafaker_examples;

import net.datafaker.Faker;

class MyCustomFaker extends Faker {

    public Turtle turtle() {
        return getProvider(Turtle.class, Turtle::new, this);
    }
}
