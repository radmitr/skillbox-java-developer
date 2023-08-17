public class RegexExample {

    public static void main(String[] args) {
        // 1
        String phone =  "+7 (978) 018-27-56";
        System.out.println(phone.replaceAll("[^0-9]", ""));
        System.out.println("-----------------------------------------------------");

        // 2
        String textNew = "Идёт переустройство мира. Вероятна мировая война. На уроне экономики она уже идёт.";
        String[] sentences = textNew.split("(?>\\.\\s|\\.)");
        for (int i = 0; i < sentences.length; i++) {
            System.out.println(sentences[i]);
        }
    }
}
