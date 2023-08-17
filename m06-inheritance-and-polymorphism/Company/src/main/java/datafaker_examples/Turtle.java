package datafaker_examples;

import net.datafaker.providers.base.AbstractProvider;
import net.datafaker.providers.base.BaseProviders;

public class Turtle extends AbstractProvider<BaseProviders> {

    private static final String[] TURTLE_NAMES = new String[]{"Leonardo", "Raphael", "Donatello", "Michelangelo"};

    public Turtle(BaseProviders faker) {
        super(faker);
    }

    public String name() {
        return TURTLE_NAMES[faker.random().nextInt(TURTLE_NAMES.length)];
    }
}
