package io.nadron.event.impl;

import io.nadron.app.Session;
import io.nadron.event.Event;
import io.nadron.event.Events;
import io.nadron.event.SessionEventHandler;


public class NewUserLoginEventListener implements SessionEventHandler {

    private static final int EVENT_TYPE = Events.NEW_USER_LOGIN;
    private final Session session;

    public NewUserLoginEventListener(Session session) {
        this.session = session;
    }

    @Override
    public void onEvent(Event event) {
        session.onEvent(event);
    }

    @Override
    public int getEventType() {
        return EVENT_TYPE;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void setSession(Session session) {
        throw new UnsupportedOperationException(
                "Session is a final field in this class. "
                        + "It cannot be reset");
    }
}
