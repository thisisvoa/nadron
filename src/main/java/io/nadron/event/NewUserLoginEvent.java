package io.nadron.event;


import io.nadron.communication.DeliveryGuaranty;

public interface NewUserLoginEvent extends Event{
    DeliveryGuaranty getDeliveryGuaranty();

    void setDeliveryGuaranty(DeliveryGuaranty deliveryGuaranty);
}
