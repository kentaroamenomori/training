package tool;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionContext;

public class MySession implements HttpSession {

    // セッションとそのIDのペアを保存するマップ
    private static Map<String, MySession> sessionIdMap = new HashMap<>();

    /**
     * 指定したIDを持つセッションを返す
     * @param id
     * @return MySession
     */
    public static MySession getSessionById(String id) {
        return sessionIdMap.get(id);
    }

    public MySession(String id) {
        sessionId = id;
        // インスタンス生成と同時にマップにIDとインスタンスを保存する
        sessionIdMap.put(id, this);
    }

    private String sessionId;

    // セッションの中身
    private Map<String, Object> values = new HashMap<>();

    @Override
    public Object getAttribute(String name) {
        return values.get(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
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

    @Override
    public String getId() {
        return sessionId;
    }

    // 以下実装不要 **************************************************

    @Override
    public long getCreationTime() {
        // TODO Auto-generated method stub
        return 0;
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
