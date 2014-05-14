package io.nadron.handlers.netty;

import io.nadron.event.Event;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This encoder will convert an incoming object (mostly expected to be an
 * {@link Event} object) to a {@link TextWebSocketFrame} object. It uses
 * {@link ObjectMapper} from jackson library to do the Object to JSon String
 * encoding.
 *
 * @author Abraham Menacherry
 */
@Sharable
public class TextWebsocketEncoder extends MessageToMessageEncoder<Event> {
    private static final Logger LOG = LoggerFactory.getLogger(TextWebsocketEncoder.class);

    private ObjectMapper jackson;

    @Override
    protected void encode(ChannelHandlerContext ctx, Event msg, List<Object> out) throws Exception {
        LOG.trace("Event: {}", msg );
        String json = jackson.writeValueAsString(msg);
        LOG.trace("json: {}", json );
        out.add(new TextWebSocketFrame(json));
    }

    public ObjectMapper getJackson() {
        return jackson;
    }

    public void setJackson(ObjectMapper jackson) {
        this.jackson = jackson;
    }

}
