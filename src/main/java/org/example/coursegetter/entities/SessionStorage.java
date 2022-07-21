package org.example.coursegetter.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SessionStorage {
    private final Map<String, Session> sessions = new HashMap<>();

    /**
     * Create a session storage.
     */
    public SessionStorage() {
    }

    public void addSession(String sessionName, Session session){
        sessions.put(sessionName, session);
    }

    /**
     * Gets a session.
     *
     * @param session the session, in a format similar to 20229 (September 2022)
     * @return the Session if one is found, or null otherwise.
     */
    public Session getSession(String session){
        if (sessions.containsKey(session)){
            return sessions.get(session);
        }
        return null;

    }
    public Set<String> listAllSessions(){
        return Collections.unmodifiableSet(sessions.keySet());
    }
}
