package rabin_karp;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RabinKarpExtended2Test {

    @Test
    void whenTextIsNull_thenException() {
        String expectedMessage = "Передан пустой текстовый фрагмент или null";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RabinKarpExtended2 rabinKarp = new RabinKarpExtended2(null);
        });
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(expectedMessage));
    }

    @Test
    void whenTextIsEmptyString_thenException() {
        String expectedMessage = "Передан пустой текстовый фрагмент или null";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RabinKarpExtended2 rabinKarp = new RabinKarpExtended2("");
        });
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(expectedMessage));
    }

    @Test
    void whenPatternIsNull_thenException() {
        RabinKarpExtended2 rabinKarp = new RabinKarpExtended2("Skillbox");
        String expectedMessage = "Передан пустой поисковый запрос или null";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rabinKarp.search(null);
        });
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(expectedMessage));
    }

    @Test
    void whenPatternIsEmptyString_thenException() {
        RabinKarpExtended2 rabinKarp = new RabinKarpExtended2("Skillbox");
        String expectedMessage = "Передан пустой поисковый запрос или null";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rabinKarp.search("");
        });
        String actualMessage = exception.getMessage();

        assertThat(actualMessage, is(expectedMessage));
    }

    @Test
    void whenSearchPatternWithOneOccurrenceInText_thenReturnSingletonIndexList() {
        RabinKarpExtended2 rabinKarpExtended2 = new RabinKarpExtended2("Skillbox");
        List<Integer> expectedIndexes = Collections.singletonList(5);

        List<Integer> actualIndexes = rabinKarpExtended2.search("box");

        assertThat(actualIndexes, is(expectedIndexes));
    }

    @Test
    void whenSearchPatternWithSomeOccurrencesInText_thenReturnIndexList() {
        RabinKarpExtended2 rabinKarpExtended2 = new RabinKarpExtended2("Skillbox Skillboxboxboy");
        List<Integer> expectedIndexes = List.of(5, 14, 17);

        List<Integer> actualIndexes = rabinKarpExtended2.search("box");

        assertThat(actualIndexes, is(expectedIndexes));
    }
}
