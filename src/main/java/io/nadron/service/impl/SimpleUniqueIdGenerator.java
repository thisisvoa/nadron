package io.nadron.service.impl;

import io.nadron.service.UniqueIDGeneratorService;
import io.nadron.util.NadronConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;


/**
 * Uses an atomic long to increment and provide a unique id. This will not work
 * in case of clustered servers.
 *
 * @author Abraham.Menacherry
 */
public class SimpleUniqueIdGenerator implements UniqueIDGeneratorService {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUniqueIdGenerator.class);

    public static final AtomicLong ID = new AtomicLong(0l);

    @Override
    public Object generate() {
        String nodeName = System.getProperty(NadronConfig.NODE_NAME);
        if (null == nodeName || "".equals(nodeName)) {
            Object id = ID.incrementAndGet();
            LOG.trace("New generated value is: {}", id.toString());
            return id;
        } else {
            Object id = nodeName + ID.incrementAndGet();
            LOG.trace("New generated nodeName value is: {}", id.toString());
            return id;
        }
    }

    @Override
    public Object generateFor(@SuppressWarnings("rawtypes") Class klass) {
        Object id = klass.getSimpleName() + ID.incrementAndGet();
        LOG.trace("New generated value generateFor is: {}", id.toString());
        return id;
    }

}
