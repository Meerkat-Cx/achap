package ar.org.sicel.googleChart;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;




public abstract class GoogleChart {

	private List<Object> data = new ArrayList<Object>();

	public GoogleChart() {
		super();
	}

	public void actionConsultar() {
		data = new ArrayList<Object>();
		data.add(getHeaderLabels());
		data.addAll(getResults());
	}

	private String getDataFormatted(List<Integer> columnsToExclude, boolean excludeLastRow) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
	
		String prefixRow = "";
		for (Iterator dataiterator = data.iterator(); dataiterator.hasNext();) {
			Object[] rowsObjs = (Object[]) dataiterator.next();
			
			if (!dataiterator.hasNext() && excludeLastRow)
				continue;
			
			buffer.append(prefixRow);
			prefixRow=",";
			
			List<Object> tmpList = new ArrayList(Arrays.asList(rowsObjs));  
		
			buffer.append("[");
			int i=-1;
			String prefix = "";
			for (Iterator iterator = tmpList.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				i++;
				if (columnsToExclude != null && columnsToExclude.contains(new Integer(i)))
					continue;
				buffer.append(prefix);
				prefix=",";
				
				if (object != null)
					if (object instanceof Number)
						buffer.append(object.toString());
					else
						buffer.append("'").append(object.toString()).append("'");
			}
			buffer.append("]");
		}
		buffer.append("]");
		return buffer.toString();
	}

	public String getTableData() {
		return getDataFormatted(null,false);
	}

	public String getChartData() {
		return getDataFormatted(getIndexColumnsExcludedInChart(),excludeLastRowInChart());
	}

	protected abstract List<Integer> getIndexColumnsExcludedInChart();

	protected abstract List<Object> getResults();

	protected abstract Object[] getHeaderLabels();
	
	protected boolean excludeLastRowInChart() {
		return false;
	}
}