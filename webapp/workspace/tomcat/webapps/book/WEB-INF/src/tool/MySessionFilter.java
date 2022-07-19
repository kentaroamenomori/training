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

        var newRequest = new MyHttpServletRequest((HttpServletRequest) request);

        // セッションIDを格納したクッキーをresponseに追加する
        var cookie = newRequest.getCookie();
        ((HttpServletResponse) response).addCookie(cookie);

        chain.doFilter(newRequest, response);
    }
    
}
