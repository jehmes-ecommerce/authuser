package com.ecommerce.authuser.publishers;

import com.ecommerce.authuser.dtos.UserEventDto;
import com.ecommerce.authuser.enums.ActionType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value(value = "${ecommerce.broker.exchange.userEvent}")
    private String userEventExchange;

    public UserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishUserEvent(UserEventDto userEventDto, ActionType actionType) {
        userEventDto.setActionType(actionType);
        rabbitTemplate.convertAndSend(userEventExchange, "", userEventDto);
    }
}
