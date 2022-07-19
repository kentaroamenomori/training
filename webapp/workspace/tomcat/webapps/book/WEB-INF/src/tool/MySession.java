package tool;

import java.util.Enumeration;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionContext;

public class MySession implements HttpSession {

    private String id = null;

    private Map<String, Object> values;

    @Override
    public Object getAttribute(String name) {
        if (id == null) return null;
        return values.get(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        System.out.println(value);
        values.put(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        values.remove(name);
    }

    @Override
    public void invalidate() {
        values.clear();
    }

    // private String getCookieValue() {
        
    // }

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
