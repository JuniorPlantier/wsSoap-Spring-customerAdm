package com.plantier.soap.ws.wsSoapcustomersAdm.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.com.plantierjr.CustomerDetail;
import br.com.plantierjr.GetCustomerDetailRequest;
import br.com.plantierjr.GetCustomerDetailResponse;

@Endpoint
public class CustomerDetailEndpoint {

	@PayloadRoot(namespace="http://plantierjr.com.br", localPart="GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse processCustomerDetailRequest(@RequestPayload GetCustomerDetailRequest req) {
		GetCustomerDetailResponse resp = new GetCustomerDetailResponse();
		
		CustomerDetail customer = new CustomerDetail();
		customer.setId(1);
		customer.setName("Rogério Ceni");
		customer.setEmail("ceni@spfc.com.br");
		customer.setPhone("11 981125756");
		resp.setCustomerDetail(customer);
		
		return resp;
	}
	
}
