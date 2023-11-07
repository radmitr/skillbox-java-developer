package rabin_karp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class RabinKarpTest {
    @ParameterizedTest
    @CsvSource({
            "This is an example for rabin karp algorithmn, algorithmn, 101",
            "AAABBDDG, AAA, 137",
            "AAABBCCBB, BBCC, 101",
            "AAABBCCBB, BBCC, 131",
            "AAAABBBBCCC, CCC, 41",
            "ABCBCBCAAB, AADB, 293",
            "Algorithm The Algorithm, Algorithm, 101"})
    void RabinKarpAlgorithmTestExample(String txt, String pat, int q) {
        int expectedIndex = txt.indexOf(pat);

        int actualIndex = RabinKarp.search(pat, txt, q);

        assertThat(actualIndex, is(expectedIndex));
    }
}
