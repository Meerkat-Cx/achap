package ar.org.sicel.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;


/**
 * A servlet filter that opens and closes a Hibernate Session for each request.
 * <p>
 * This filter guarantees a sane state, committing any pending database
 * transaction once all other filters (and servlets) have executed. It also
 * guarantees that the Hibernate <tt>Session</tt> of the current thread will
 * be closed before the response is send to the client.
 * <p>
 * Use this filter for the <b>session-per-request</b> pattern and if you are
 * using <i>Detached Objects</i>.
 *
 * @author <a href="mailto:diegopalmisano@yahoo.com.ar">Diego Palmisano</a>
 *
 * @web.filter
 *      name="hibernateFilter"
 *      display-name="Filtro dependiente de la funcionalidad de Hibernate"
 *
 * @web.filter-mapping
 *      servlet-name="StrutsActionServlet"
 *
 */
public class HibernateFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    
    //  copia para Tomcat
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    		HttpServletRequest req = (HttpServletRequest) request;
            // No existe la apertura explicita de una session en esta clase, el
            // primer llamado a HibernateUtil.beginTransaction(), el cual es realizado
            // por cualquier clase DAO, generará la apertura de la misma.
            try {
            	//StandaloneHibernateStrategy.getInstance().openNewSession();
            	StandaloneHibernateStrategy.getInstance().getCurrentSession();
            	HttpServletRequestWrapper hsrw = new HttpServletRequestWrapper(req);
                chain.doFilter(hsrw, response);

                // Commit cualquier database transaction pendiente.
                //JBossHibernateStrategy.getInstance().commitTransaction();
                StandaloneHibernateStrategy.getInstance().commitCurrentSession();
                
                
            } catch (Exception e) {
            	e.printStackTrace();
                StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
            }   
           
        }
    
    
    
    
    
    
    
    /*
    //original para Jboss
    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException, ServletException {

        // No existe la apertura explicita de una session en esta clase, el
        // primer llamado a HibernateUtil.beginTransaction(), el cual es realizado
        // por cualquier clase DAO, generará la apertura de la misma.
        try {
            chain.doFilter(request, response);

            // Commit cualquier database transaction pendiente.
            JBossHibernateStrategy.getInstance().commitTransaction();
        } finally {
            // Sin importar lo que pase, cerramos la sessión
            try {
                JBossHibernateStrategy.getInstance().closeSession();
            } catch (HibernateException ex){
                ex.printStackTrace();
            }
        }
    }
    */

    public void destroy() {
    }
}
