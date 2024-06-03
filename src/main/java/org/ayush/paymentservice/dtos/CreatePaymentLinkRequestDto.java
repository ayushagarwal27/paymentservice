package org.ayush.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePaymentLinkRequestDto {
    private Long orderID;
}
