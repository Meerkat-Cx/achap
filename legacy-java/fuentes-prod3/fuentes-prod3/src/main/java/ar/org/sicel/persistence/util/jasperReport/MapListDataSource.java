package ar.org.sicel.persistence.util.jasperReport;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class MapListDataSource implements JRDataSource {

    private Iterator iterator;
    private Object object;

    static 
    {
        Logger.getLogger(MapListDataSource.class);
    }

    public MapListDataSource(List list)
    {
        iterator = list.iterator();
    }

    public Object getFieldValue(JRField jrfield)
        throws JRException
    {
        return ((Map)object).get(jrfield.getName());
    }

    public boolean next()
        throws JRException
    {
        object = iterator.hasNext() ? iterator.next() : null;
        return object != null;
    }

}
