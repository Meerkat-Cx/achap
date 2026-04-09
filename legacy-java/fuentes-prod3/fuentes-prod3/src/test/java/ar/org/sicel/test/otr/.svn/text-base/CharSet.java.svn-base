/*
 * Creado el 30/07/2005
 */
package ar.org.sicel.test.otr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

import junit.framework.TestCase;

/**
 * @author ala
 */
public class CharSet extends TestCase {

    private final static String encu8="UTF-8";
    private final static String enciso="ISO-8859-1";
    
    public void testTransicion() throws Exception {
        //no anda
        File salida1 = new File("C:\\resultado1.xml");
        FileWriter out1 = new FileWriter(salida1);
        out1.write("<pru>");
        out1.write("hola αινσϊ");
        out1.write("</pru>");
        out1.flush();
        out1.close();
        //ok
        File salida2 = new File("C:\\resultado2.xml");
        BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(salida2), encu8));
        out2.write("<?xml version=\"1.0\" encoding=\""+encu8+"\"?>");
        out2.write("<pru>");
        out2.write("hola αινσϊ");
        out2.write("</pru>");
        out2.flush();
        out2.close();
        //ok
        File salida3 = new File("C:\\resultado3.xml");
        BufferedWriter out3 = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(salida3), enciso));
        out3.write("<?xml version=\"1.0\" encoding=\""+enciso+"\"?>");
        out3.write("<pru>");
        out3.write("hola αινσϊ");
        out3.write("</pru>");
        out3.flush();
        out3.close();
        //ok
        File salida4 = new File("C:\\resultado4.xml");
        FileWriter out4 = new FileWriter(salida4);
        out4.write("<?xml version=\"1.0\" encoding=\""+enciso+"\"?>");
        out4.write("<pru>");
        out4.write("hola αινσϊ");
        out4.write("</pru>");
        out4.flush();
        out4.close();
    }
}
