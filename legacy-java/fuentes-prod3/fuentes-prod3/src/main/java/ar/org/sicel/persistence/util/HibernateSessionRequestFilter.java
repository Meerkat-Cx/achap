//package ar.org.sicel.persistence.util;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.hibernate.SessionFactory;
//
//public class HibernateSessionRequestFilter implements Filter {
//
//	private static Log log = LogFactory
//			.getLog(HibernateSessionRequestFilter.class);
//
//	private SessionFactory sf;
//
//	public void doFilter(ServletRequest request, ServletResponse response,
//			FilterChain chain) throws IOException, ServletException {
//		try {
//			chain.doFilter(request, response);
//			HibernateUtil.commitTransaction();
//		} finally {
//			HibernateUtil.closeSession();
//		}
//	}
//
//	public void init(FilterConfig arg0) throws ServletException {
//
//	}
//
//	public void destroy() {
//	
//	}
//
//}