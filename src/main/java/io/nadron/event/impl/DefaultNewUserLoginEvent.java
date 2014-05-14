package io.nadron.event.impl;


import io.nadron.communication.DeliveryGuaranty;
import io.nadron.event.Event;
import io.nadron.event.Events;
import io.nadron.event.NewUserLoginEvent;

public class DefaultNewUserLoginEvent extends DefaultEvent implements NewUserLoginEvent {
    private DeliveryGuaranty guaranty = DeliveryGuaranty.DeliveryGuarantyOptions.RELIABLE;

    private static final long serialVersionUID = 6486254029799527687L;

    /**
     * Default constructor which will set the {@link DeliveryGuaranty} to
     * RELIABLE. It will also set the type of the event to
     * {@link io.nadron.event.Events#NEW_USER_LOGIN}.
     */
    public DefaultNewUserLoginEvent() {
        super.setType(Events.NEW_USER_LOGIN);
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
    public DefaultNewUserLoginEvent(Event event) {
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
    public DefaultNewUserLoginEvent(Event event, DeliveryGuaranty deliveryGuaranty) {
        this.setSource(event.getSource());
        this.setEventContext(event.getEventContext());
        this.setTimeStamp(event.getTimeStamp());
        this.guaranty = DeliveryGuaranty.DeliveryGuarantyOptions.RELIABLE;
        super.setType(Events.NEW_USER_LOGIN);
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
                "Event type of this class is already set to NEW_USER_LOGIN. "
                        + "It should not be reset.");
    }
}
