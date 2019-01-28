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
        System.out.println("============================================================================");
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser p = saxFactory.newSAXParser();
        DefaultHandler dh = new DefaultHandler() {
            Company c3 = null;
            Department d = null;
            Employee e = null;

            boolean isInDirector = false;
            boolean isInCorrectEmplyee = false;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equals("company")) {
                    c3 = new Company();
                    c3.id = Integer.parseInt(attributes.getValue("id"));
                    c3.name = attributes.getValue("name");
                }
                if (qName.equals("director")) {
                    isInDirector = true;
                }
                if (qName.equals("department")) {
                    d = new Department();
                    d.id = Integer.parseInt(attributes.getValue("id"));
                    d.name = attributes.getValue("name");
                }
                if (qName.equals("employee")) {
                    e = new Employee();
                    e.id = Integer.parseInt(attributes.getValue("id"));
                    isInCorrectEmplyee = true;
                }

            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {

                if (qName.equals("director")) {
                    isInDirector = false;
                }
                if (qName.equals("employee")) {
                    d.emplyees.add(e);
                    e = null;
                    isInCorrectEmplyee = false;
                }
                if (qName.equals("department")) {
                    c3.departments.add(d);
                    d = null;
                }
                if (qName.equals("company")) {
                    System.out.println(c3);
                }

            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (isInDirector) {
                    String s = "";
                    for (int i = start; i < start + length; i++) {
                        s += ch[i];
                    }
                    c3.director = s;
                }
                if (isInCorrectEmplyee) {
                    String s = "";
                    for (int i = start; i < start + length; i++) {
                        s += ch[i];
                    }
                    e.name = s;

                }
            }
        };
        p.parse(f2, dh);
    }
}
