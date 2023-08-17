import java.util.Arrays;

// 4.5.2
public class TextSplitterLoader {

    public static void main(String[] args) {
        String text = "Who doesn't love music? Whatever mood we have - bad or good, " +
                "from time to time we turn on our favorite song. And it, in turn, " +
                "either calms us, or on the contrary, makes us fall into melancholy.\n" +
                "\n" +
                "Life without music would be boring and dull. Just imagine a disco " +
                "being in silence or holidays being without musicians. Absolutely all " +
                "people of every type around the world are not indifferent to the music. " +
                "Genres that are most popular are rock, rap, pop, techno, chanson, " +
                "hip-hop and others. There are other very specific genres. For example, " +
                "folk music, classical music, spiritual music and also such interesting " +
                "genres as blues and jazz.\n" +
                "\n" +
                "Have you ever noticed that people, who share your tastes in music, get " +
                "along with you much faster than those whose tastes are very different " +
                "from yours? Many people believe that music is first and foremost an " +
                "instrument of the soul. Probably it is so.\n" +
                "\n" +
                "Most of all I like modern music, but I also will not refuse to listen to " +
                "classical and ethnic music. For example, I like Spanish folk songs. Listening " +
                "to foreign songs I improve my knowledge of English. This is a great way " +
                "to mix business with pleasure.";

        text = text.replaceAll("[?,.-]", "");
        String[] words = text.split("\\s+");

        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }
    }
}
