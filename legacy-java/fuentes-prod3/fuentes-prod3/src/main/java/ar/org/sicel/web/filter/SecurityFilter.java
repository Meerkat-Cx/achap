package ar.org.sicel.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ar.org.sicel.persistence.Funcion;
import ar.org.sicel.persistence.FuncionDAO;
import ar.org.sicel.persistence.Rol;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.web.Tokens;

public class SecurityFilter implements Filter {

    
    private String APPLICATION_NAME;
    private String LOGIN_PAGE;
    private String INDEX_PAGE;
    private String ERROR_PAGE;

    private static final Logger log;
    private Map<String,String> freeAccess;

    static{
        log = Logger.getLogger(SecurityFilter.class);
    }

    public void init(FilterConfig config) throws ServletException {
        APPLICATION_NAME = config.getInitParameter(Tokens.CONTEXT_ROOT_PARAM);
        if (APPLICATION_NAME == null)
            throw new ServletException(
                "Por favor, complete el valor del parámetro "
                + Tokens.CONTEXT_ROOT_PARAM);
        LOGIN_PAGE = "/" + APPLICATION_NAME + "/login.do";
        INDEX_PAGE = "/" + APPLICATION_NAME + "/index.jsp";
        ERROR_PAGE = "/" + APPLICATION_NAME + "/error.do";

        freeAccess = new HashMap<String,String>();
        freeAccess.put("index.do", null);
        freeAccess.put("main.do", null);
        freeAccess.put("login.do", null);
        freeAccess.put("error.do", null);
        freeAccess.put("imagen.do", null);
        freeAccess.put("logout.do", null);
        freeAccess.put("cambiarClavePage.do", null);
        freeAccess.put("cambiarClaveAction.do", null);
        freeAccess.put("enterLogin.do", null);
        freeAccess.put("quienesSomos.do", null);
        freeAccess.put("glosario.do", null);
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Usuario usuario = (Usuario)request.getSession().getAttribute("CURRENTUSER");
        String requestUri = request.getRequestURI();

        if (!requestUri.equals(INDEX_PAGE) && !requestUri.equals(LOGIN_PAGE)) {
            // usuario sin sesion
            if(usuario == null){
                response.sendRedirect(LOGIN_PAGE);
                return;
            } else {
                if (usuario.getId() != null) {
                    if(isUserAuthorizedToURL(usuario,requestUri)){
                        log.info("Se a autorizado el acceso a USUARIO DE SESSION " + usuario.getUsername() + ", ROL "+usuario.getRol().getNombre()+ " a URL " + requestUri);
                    	request.getSession().setAttribute("USERTIMEOUT",false);
                    	chain.doFilter(request, response);
                    }else{
                        log.info("Se a denegado el acceso a USUARIO DE SESSION " + usuario.getUsername() + ", ROL "+usuario.getRol().getNombre()+ " a URL " + requestUri);
                        response.sendRedirect(ERROR_PAGE);
                        return;
                    }
                } else {
                    if (request.getSession().isNew() == true) {
                        request.getSession().setAttribute("USERTIMEOUT",true);
                        response.sendRedirect(LOGIN_PAGE);
                        return;
                    } else {
                        if (usuario.getId() == null) {
                            log.debug("La sesion no es null pero el usuario es null, Redirecciona -> "
                                + LOGIN_PAGE);
                            response.sendRedirect(LOGIN_PAGE);
                            return;
                        }
                    }
                }
            }
        } else {
        	HttpServletRequestWrapper hsrw = new HttpServletRequestWrapper(request);
            chain.doFilter(hsrw, response);
        }
    }
    
    /**
     * Valida que el usuario tenga autorizacion.
     * @param usuario
     * @param link
     * @return
     */
    private boolean isUserAuthorizedToURL(Usuario usuario, String link){
     	link=link.replace("/"+APPLICATION_NAME+"/","");
    	return (freeAccess.containsKey(link) || isRolAuthorizedToURL(usuario.getRol(),link));
    }
    
    /**
     * Valida si el rol tiene autorizacion al link.
     * @param rol
     * @param link
     * @return
     */
    private boolean isRolAuthorizedToURL(Rol rol, String link){
    	Funcion funcion=FuncionDAO.findNombre(link);
    	Set funciones= rol.getFunciones();
    	return funciones.contains(funcion);
    }

}
