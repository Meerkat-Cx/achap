package ar.org.sicel.googleChart;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import ar.org.sicel.persistence.EvtLactanciaDAO;
import ar.org.sicel.persistence.Hembra;

public class ControlesTotalesLactanciasChart extends GoogleChart {
	
	private Hembra hembra= null;
	@Override
	
	protected Object[] getHeaderLabels(){
		ResourceBundle res = ResourceBundle.getBundle("ApplicationResources_es_ES");
		return new Object[]{res.getString("chart.controles.lactancia.tipoEvento")+"-"+res.getString("chart.controles.lactancia.fechaEvento"),
							res.getString("chart.controles.lactancia.valorLeche")};
		
	}
	protected List<Integer> getIndexColumnsExcludedInChart(){
		//return Arrays.asList( new Integer[]{0,2});
		return null;
	}
	
	protected List<Object> getResults(){
		return EvtLactanciaDAO.getLecheTotalControlesLactancias(this.getHembra());
	}
	public Hembra getHembra() {
		return hembra;
	}
	public void setHembra(Hembra hembra) {
		this.hembra = hembra;
	}
}
	