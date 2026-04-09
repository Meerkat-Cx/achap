package org.extremecomponents.table.cell;

import java.util.Collection;

import org.apache.commons.beanutils.BeanUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.ColumnBuilder;
/**
 * Clase que se utiliza para la generacion/administracion de la tabla de busquedas de eclos
 * @author jdivars
 *
 */
public class SelectedEcloCell implements Cell {

	    public String getExportDisplay(TableModel model, Column column) {
	        return null;
	    }

	    public String getHtmlDisplay(TableModel model, Column column) {
	        ColumnBuilder columnBuilder = new ColumnBuilder(column);
	        
	        columnBuilder.tdStart();
	        
	        try {
	            Object bean = model.getCurrentRowBean();
	            String presidentId = BeanUtils.getProperty(bean, "id");
	            
	            Collection selectedPresidentsIds = (Collection)model.getContext().getSessionAttribute("selected_eclos");
	            if (selectedPresidentsIds != null && selectedPresidentsIds.contains(presidentId)) {
	            	
	                columnBuilder.getHtmlBuilder().input("hidden").name("chkbx_" + presidentId).value("SELECTED").xclose();
	                columnBuilder.getHtmlBuilder().input("checkbox").name(BeanUtils.getProperty(bean, "id"));
	                columnBuilder.getHtmlBuilder().onclick("setEcloState(this)");
	                columnBuilder.getHtmlBuilder().checked();
	                columnBuilder.getHtmlBuilder().xclose();
	            } else {
	            	
	                columnBuilder.getHtmlBuilder().input("hidden").name("chkbx_" + presidentId).value("UNSELECTED").xclose();
	                columnBuilder.getHtmlBuilder().input("checkbox").name(BeanUtils.getProperty(bean, "id"));
	                columnBuilder.getHtmlBuilder().onclick("setEcloState(this)");
	                columnBuilder.getHtmlBuilder().xclose();
	            }
	        } catch (Exception e) {}
	        
	        columnBuilder.tdEnd();
	        
	        return columnBuilder.toString();
	    }

}
