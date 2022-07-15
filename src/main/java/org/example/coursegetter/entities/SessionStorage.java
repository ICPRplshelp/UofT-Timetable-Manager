package org.example.coursegetter.entities;

import java.util.HashMap;
import java.util.Map;

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
        return sessions.get(session);
    }
}
