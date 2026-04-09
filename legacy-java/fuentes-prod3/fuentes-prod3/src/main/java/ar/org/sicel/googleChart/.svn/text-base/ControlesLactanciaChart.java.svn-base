package ar.org.sicel.googleChart;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import ar.org.sicel.persistence.EvtLactancia;
import ar.org.sicel.persistence.EvtLactanciaDAO;

public class ControlesLactanciaChart extends GoogleChart {
	
	private EvtLactancia lactancia= null;
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
		return EvtLactanciaDAO.getLecheLactanciaChart(this.getLactancia());
	}

	public EvtLactancia getLactancia() {
		return lactancia;
	}

	public void setLactancia(EvtLactancia lactancia) {
		this.lactancia = lactancia;
	}

}
