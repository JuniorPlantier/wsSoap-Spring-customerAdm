package com.plantier.soap.ws.wsSoapcustomersAdm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plantier.soap.ws.wsSoapcustomersAdm.bean.Customer;
import com.plantier.soap.ws.wsSoapcustomersAdm.helper.Status;
import com.plantier.soap.ws.wsSoapcustomersAdm.repository.CustomerRepository;

@Component
public class CustomerDetailService {
	
	/*
	private static List<Customer> customers = new ArrayList<>();
	
	static {
		
		Customer c1 = new Customer();
		c1.setId(1);
		c1.setName("Rogério Ceni");
		c1.setEmail("ceni@spfc.com.br");
		c1.setPhone("11 981125756");
		customers.add(c1);
		
		Customer c2 = new Customer(2,"Natasha","naty@petshop.com.br","14 987548589");
		customers.add(c2);
		Customer c3 = new Customer(3,"Felipe","felipe.pires@ponte.com.br","15 12365487");
		customers.add(c3);
		Customer c4 = new Customer(4,"Karen Elen","karen@prefeitura.com.br","14 545874523");
		customers.add(c4);
	}
	*/
	
	@Autowired
	private CustomerRepository customerRepository; 
	
	public Customer findById(Integer id) {
		Optional<Customer> customer = customerRepository.findById(id); 
	    //customers.stream().filter(c -> c.getId() == id).findAny();
		if(customer.isPresent()) {
			return customer.get();
		}
		return null;
	}
	
	public List<Customer> findAll() {
		//return customers;
		return customerRepository.findAll();
	}
	
	public Status deleteById(Integer id) {
		/*
		Customer customer = this.findById(id);
		if(customer != null) {
			customers.remove(customer);
			return Status.SUCCESS;
		}
		return Status.FAILURE;
		*/
		try {
			customerRepository.deleteById(id);
			return Status.SUCCESS;
		} catch(Exception e) {
			return Status.FAILURE;
		}
	}
	
	public Status insert(Customer customer) {
		//customers.add(customer);
		//return Status.SUCCESS;
		try {
			customerRepository.save(customer);
			return Status.SUCCESS;
		} catch(Exception e) {
			return Status.FAILURE;
		}
	}
	
}
