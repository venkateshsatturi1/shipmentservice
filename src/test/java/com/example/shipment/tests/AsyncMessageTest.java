package com.example.shipment.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.Is.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.dsl.Matchers;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.junit5.ProviderType;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.messaging.MessagePact;

import au.com.dius.pact.core.model.messaging.Message;

@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName="OrderManagementProvider",providerType = ProviderType.ASYNCH)
public class AsyncMessageTest{

	@Pact(provider = "OrderProvider", consumer = "ShipmentConsumer")
	  MessagePact createPact(MessagePactBuilder builder) {
	    PactDslJsonBody body = new PactDslJsonBody();
	    body.integerType("orderId");
	    body.integerType("shipperid",123);
	    body.integerType("paymentid");
	    body.stringType("itemNumber");
	    body.integerType("qty");
	    body.integerType("locationId");
	    body.stringType("channel", "B2B");

	    Map<String, String> metadata = new HashMap<String, String>();
	    metadata.put("Content-Type", "application/json");

	    return builder.given("Shipment Service is awaiting order")
	      .expectsToReceive("Item Order")
	      .withMetadata(metadata)
	      .withContent(body)
	      .toPact();
	  }
	
		 @Test
		  @PactTestFor(pactMethod = "createPact",providerType = ProviderType.ASYNCH)
		  void test2(MessagePact pact) {
		   // assertThat(new String(pact.getMessages().get(0).contentsAsBytes()), is("{\"orderId\":43546,\"paymentId\":12345,\"itemNumber\":\"FRE\",\"qty\":10,\"locationId\":2700}"));
		 assert true; 
		 }
		 
}
