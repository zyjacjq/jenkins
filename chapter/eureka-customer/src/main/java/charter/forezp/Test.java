package charter.forezp;

import org.dom4j.DocumentException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by JF on 2019/7/9.
 */
public class Test {
    public static void main(String[] args) throws DocumentException, IOException {

//        File file = new File("test.xml");
//        SAXReader sax = new SAXReader();
//        Document read = sax.read(file);
//        Element rootElement = read.getRootElement();
//        for(Iterator i=rootElement.elementIterator("book");i.hasNext();){
//            Element next = (Element) i.next();
//            String title = next.elementText("title");
//            String author = next.elementText("author");
//        }


//        Properties props = new Properties();
//        props.setProperty("email.support", "donot-spam-me@nospam.com");
//
//        //where to store?
//        OutputStream os = new FileOutputStream("d:/email-configuration.xml");
//
//        //store the properties detail into a pre-defined XML file
//        props.storeToXML(os, "Support Email","UTF-8");
//
//        System.out.println("Done");

        Properties props = new Properties();

        InputStream is = new FileInputStream("d:/email-configuration.xml");
        //load the xml file into properties format
        props.loadFromXML(is);

        String email = props.getProperty("email.support");

        System.out.println(email);


    }

    public static void xmlToPro(){

    }
}
