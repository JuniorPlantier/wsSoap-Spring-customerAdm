package com.plantier.soap.ws.wsSoapcustomersAdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plantier.soap.ws.wsSoapcustomersAdm.bean.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
