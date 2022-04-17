package web.tests;

import javax.servlet.*;
import java.io.IOException;

public class SecurityFilter implements Filter {

    private boolean active = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String act = filterConfig.getInitParameter("active");
        if(act!=null){
            active=(act.toUpperCase().equals("TRUE"));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(active){

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
