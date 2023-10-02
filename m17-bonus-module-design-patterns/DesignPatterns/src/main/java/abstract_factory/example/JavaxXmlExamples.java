package abstract_factory.example;

public class JavaxXmlExamples {

    public static void main(String[] args) {
        // 1
        javax.xml.parsers.DocumentBuilderFactory factory1 =
                javax.xml.parsers.DocumentBuilderFactory.newInstance();

        String provider =
                "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl";

        // create a new DocumentBuilderFactory from an implementation
        javax.xml.parsers.DocumentBuilderFactory factory2 =
                javax.xml.parsers.DocumentBuilderFactory.newInstance(provider, null);

        // 2
        javax.xml.transform.TransformerFactory.newInstance();

        // 3
        javax.xml.xpath.XPathFactory.newInstance();
    }
}
