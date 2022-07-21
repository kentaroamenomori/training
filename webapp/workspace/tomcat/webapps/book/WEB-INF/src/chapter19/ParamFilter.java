package chapter19;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class ParamFilter implements Filter {
    private String message;

    @Override
    public void init(FilterConfig config) throws ServletException {
        message = config.getInitParameter("message");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var out = response.getWriter();
        out.println(message);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
