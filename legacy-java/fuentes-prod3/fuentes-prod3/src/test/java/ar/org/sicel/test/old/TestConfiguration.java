package ar.org.sicel.test.old;


import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;

import ar.org.sicel.util.Sicel3Conf;

/*
 * Created on 13/04/2005
 *
 *  To change the template for this generated file go to
 * 
 */

/**
 * @author pablo
 */
public class TestConfiguration {

	public static void main(String[] args) {
		try {
			Sicel3Conf conf = Sicel3Conf.getConf();
			Configuration c = conf.getConfProcEstablecimientos();
			System.out.println("Esta:" + c);
			Configuration[] handles = c.getChild("handlers").getChildren("handler");
			for(int i=0;i<handles.length;i++) {
				System.out.println(handles[i].getChild("castor-class").getValue());
				System.out.println(handles[i].getChild("handler-class").getValue());					
			}
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}
