package tool;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * requestを自作のMyHttpServletRequestに変換するフィルター
 */
public class MySessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // requestをgetSession()の動作を変更した自作requestクラスに変換
        var newRequest = new MyHttpServletRequest(
            (HttpServletRequest) request, (HttpServletResponse) response);

        chain.doFilter(newRequest, response);
    }
    
}
