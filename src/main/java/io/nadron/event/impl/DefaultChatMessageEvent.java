package io.nadron.event.impl;


import io.nadron.communication.DeliveryGuaranty;
import io.nadron.event.ChatMessageEvent;
import io.nadron.event.Event;
import io.nadron.event.Events;

public class DefaultChatMessageEvent extends DefaultEvent implements ChatMessageEvent {
    private DeliveryGuaranty guaranty = DeliveryGuaranty.DeliveryGuarantyOptions.RELIABLE;

    private static final long serialVersionUID = 6487554029269527687L;


    /**
     * Default constructor which will set the {@link DeliveryGuaranty} to
     * RELIABLE. It will also set the type of the event to
     * {@link io.nadron.event.Events#NEW_USER_LOGIN}.
     */
    public DefaultChatMessageEvent() {
        super.setType(Events.CHAT_MESSAGE);
        this.guaranty = DeliveryGuaranty.DeliveryGuarantyOptions.RELIABLE;
    }

    /**
     * Copy constructor which will take values from the event and set it on this
     * instance. It will disregard the type of the event and set it to
     * {@link Events#NEW_USER_LOGIN}. {@link io.nadron.communication.DeliveryGuaranty.DeliveryGuarantyOptions} is set to
     * RELIABLE.
     *
     * @param event The instance from which payload, create time etc will be
     *              copied
     */
    public DefaultChatMessageEvent(Event event) {
        this(event, DeliveryGuaranty.DeliveryGuarantyOptions.RELIABLE);
    }

    /**
     * Copy constructor which will take values from the event and set it on this
     * instance. It will disregard the type of the event and set it to
     * {@link Events#NEW_USER_LOGIN}. {@link io.nadron.communication.DeliveryGuaranty.DeliveryGuarantyOptions} is set to the
     * value passed in
     *
     * @param event            The instance from which payload, create time etc will be
     *                         copied
     * @param deliveryGuaranty
     */
    public DefaultChatMessageEvent(Event event, DeliveryGuaranty deliveryGuaranty) {
        this.setSource(event.getSource());
        this.setEventContext(event.getEventContext());
        this.setTimeStamp(event.getTimeStamp());
        this.guaranty = DeliveryGuaranty.DeliveryGuarantyOptions.RELIABLE;
        super.setType(Events.CHAT_MESSAGE);
    }

    @Override
    public DeliveryGuaranty getDeliveryGuaranty() {
        return guaranty;
    }

    @Override
    public void setDeliveryGuaranty(DeliveryGuaranty deliveryGuaranty) {
        this.guaranty = deliveryGuaranty;
    }

    @Override
    public void setType(int type) {
        throw new IllegalArgumentException(
                "Event type of this class is already set to CHAT_MESSAGE. "
                        + "It should not be reset.");
    }
}
