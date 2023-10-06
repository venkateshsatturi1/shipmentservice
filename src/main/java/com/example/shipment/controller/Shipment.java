package com.example.shipment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.shipment.dto.DataF;
import com.example.shipment.dto.ItemOrder;
import com.example.shipment.dto.Order;

@Service
public class Shipment {
	private static final Logger LOGGER=LoggerFactory.getLogger(Shipment.class);

	@RabbitListener(queues = {"${rabbitmq.queue.name}"})
	public void consumeOrder(ItemOrder data) {
		LOGGER.info(String.format("Message received from queue successfully.... Order id is %s", data.getOrderId()));
		
	}

}
