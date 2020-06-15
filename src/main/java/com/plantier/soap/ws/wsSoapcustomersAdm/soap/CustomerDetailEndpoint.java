package com.plantier.soap.ws.wsSoapcustomersAdm.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.plantier.soap.ws.wsSoapcustomersAdm.bean.Customer;
import com.plantier.soap.ws.wsSoapcustomersAdm.service.CustomerDetailService;

import br.com.plantierjr.CustomerDetail;
import br.com.plantierjr.DeleteCustomerRequest;
import br.com.plantierjr.DeleteCustomerResponse;
import br.com.plantierjr.GetAllCustomerDetailRequest;
import br.com.plantierjr.GetAllCustomerDetailResponse;
import br.com.plantierjr.GetCustomerDetailRequest;
import br.com.plantierjr.GetCustomerDetailResponse;

@Endpoint
public class CustomerDetailEndpoint {

	@Autowired
	CustomerDetailService service;
	
	@PayloadRoot(namespace="http://plantierjr.com.br", localPart="GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse processCustomerDetailRequest(@RequestPayload GetCustomerDetailRequest req) throws Exception {
		Customer customer = service.findById(req.getId());
		if(customer == null) {
			throw new Exception("Invalid Customer Id: " + req.getId());
		}
		return convertToGetCustomerDetailResponse(customer);
	}
	
	@PayloadRoot(namespace="http://plantierjr.com.br", localPart="GetAllCustomerDetailRequest")
	@ResponsePayload
	public GetAllCustomerDetailResponse processGetAllCustomerDetailResponse(@RequestPayload GetAllCustomerDetailRequest req) {
		List<Customer> customers = service.findAll();
		return convertToGetAllCustomerDetailResponse(customers);
	}
	
	@PayloadRoot(namespace="http://plantierjr.com.br", localPart="DeleteCustomerRequest")
	@ResponsePayload
	public DeleteCustomerResponse deleteCustomerRequest(@RequestPayload DeleteCustomerRequest req) {
		DeleteCustomerResponse resp = new DeleteCustomerResponse();
		resp.setStatus(convertToStatusSoap(service.deleteById(req.getId())));
		return resp;
	}
	
	private GetCustomerDetailResponse convertToGetCustomerDetailResponse(Customer customer) {
		GetCustomerDetailResponse resp = new GetCustomerDetailResponse();
		resp.setCustomerDetail(convertToCustomerDetail(customer));
		return resp;
	}
	
	private GetAllCustomerDetailResponse convertToGetAllCustomerDetailResponse(List<Customer> customers) {
		GetAllCustomerDetailResponse resp = new GetAllCustomerDetailResponse();
		customers.stream().forEach(c -> resp.getCustomerDetail().add(convertToCustomerDetail(c)));
		return resp;
	}
	
	private CustomerDetail convertToCustomerDetail(Customer customer) {
		CustomerDetail cd = new CustomerDetail();
		cd.setId(customer.getId());
		cd.setName(customer.getName());
		cd.setPhone(customer.getPhone());
		cd.setEmail(customer.getEmail());
		return cd;
	}
	
	private br.com.plantierjr.Status convertToStatusSoap(com.plantier.soap.ws.wsSoapcustomersAdm.helper.Status statusService) {
		
		if(statusService == com.plantier.soap.ws.wsSoapcustomersAdm.helper.Status.FAILURE) {
			return br.com.plantierjr.Status.FAILURE;
		} else {
			return br.com.plantierjr.Status.SUCCESS;
		}
	}
}
