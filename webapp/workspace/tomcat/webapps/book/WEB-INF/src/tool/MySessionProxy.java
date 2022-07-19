package tool;

import java.util.Enumeration;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionContext;

public class MySessionProxy implements HttpSession {

    private static HttpSession session;

    @Override
    public Object getAttribute(String name) {
        if (session == null) {
            session = new MySession();
        }
        return session.getAttribute(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        if (session == null) {
            session = new MySession();
        }
        session.setAttribute(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        if (session == null) {
            session = new MySession();
        }
        session.removeAttribute(name);
    }

    @Override
    public void invalidate() {
        if (session == null) {
            session = new MySession();
        }
        session.invalidate();
    }

    // 以下実装不要 **************************************************

    @Override
    public long getCreationTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getLastAccessedTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getMaxInactiveInterval() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getValue(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String[] getValueNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putValue(String name, Object value) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeValue(String name) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isNew() {
        // TODO Auto-generated method stub
        return false;
    }
}
