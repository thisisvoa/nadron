package io.ybt.lostdecade;

import io.nadron.event.impl.ChatMessage;
import io.nadron.event.impl.DefaultEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LDEvent extends DefaultEvent {
    private static final Logger LOG = LoggerFactory.getLogger(LDEvent.class);
    private static final long serialVersionUID = 1L;

    private ChatMessage source;

    @Override
    public ChatMessage getSource() {
        return source;
    }

    public void setSource(ChatMessage source) {
        this.source = source;
    }

}
