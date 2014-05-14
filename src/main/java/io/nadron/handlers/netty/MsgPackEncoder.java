package io.nadron.handlers.netty;

import io.nadron.event.Event;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.msgpack.MessagePack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Sharable
public class MsgPackEncoder extends MessageToMessageEncoder<Event> {
    private static final Logger LOG = LoggerFactory.getLogger(MsgPackEncoder.class);
    MessagePack msgPack;

    @Override
    protected void encode(ChannelHandlerContext ctx, Event event, List<Object> out) throws Exception {
        ByteBuf msg = null;
        if (null != event.getSource()) {
            LOG.trace("Event class: {}", event.getClass());
            ByteBuf buf = ctx.alloc().buffer(1);
            buf.writeByte(event.getType());
            msg = Unpooled.wrappedBuffer(buf, Unpooled.wrappedBuffer(msgPack.write(event.getSource())));
        } else {
            msg = ctx.alloc().buffer(1);
            msg.writeByte(event.getType());
        }
        out.add(msg);
    }

    public MessagePack getMsgPack() {
        return msgPack;
    }

    public void setMsgPack(MessagePack msgPack) {
        this.msgPack = msgPack;
    }

}
