import com.google.gson.Gson;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, JAXBException, ParserConfigurationException, SAXException {
        Gson gson = new Gson();
        File f = new File("company.json");
        StringBuilder s = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(f));
        String str;
        while ((str = br.readLine()) != null) {
            s.append(str);
        }
        br.close();
        System.out.println(s);
        Company c = gson.fromJson(s.toString(), Company.class);
        System.out.println(c);
        String from = gson.toJson(c);
        System.out.println(from);

        JAXBContext context = JAXBContext.newInstance(Company.class);
        Marshaller m = context.createMarshaller();
        File f2 = new File("company.xml");
        f2.createNewFile();
        m.marshal(c, f2);

        br = new BufferedReader(new FileReader(f2));
        String xml = br.readLine();
        br.close();
        System.out.println(xml);

        Unmarshaller um = context.createUnmarshaller();
        Company c2 = (Company) um.unmarshal(f2);

        System.out.println(c2);

        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser p = saxFactory.newSAXParser();
        DefaultHandler dh = new DefaultHandler() {
            boolean isInCorrectDepartment = false;
            boolean isInCorrectEmplyee = false;
            boolean isInName = false;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equals("department") && attributes.getValue("id").equals("2")) {
                    isInCorrectDepartment = true;
                }
                if (isInCorrectDepartment && qName.equals("employee") && attributes.getValue("id").equals("2")) {
                    isInCorrectEmplyee = true;
                }
                if (isInCorrectEmplyee && qName.equals("name")) {
                    isInName = true;
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (qName.equals("department")) {
                    isInCorrectDepartment = false;
                }
                if (qName.equals("employee")) {
                    isInCorrectEmplyee = false;
                }
                if (qName.equals("name")) {
                    isInName = false;
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (isInName) {
                    String s = "";
                    for (int i = start; i < start + length; i++) {
                        s += ch[i];
                    }
                    System.out.println(s);
                }
            }
        };
        p.parse(f2, dh);
    }
}
