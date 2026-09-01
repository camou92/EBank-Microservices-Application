package com.camoutech.customerservice.service;

import com.camoutech.customerservice.entities.Customer;
import com.camoutech.customerservice.repository.CustomerRepository;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @McpTool(description = "Get All customers")
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @McpTool(description = "Find a customer by id")
    public Customer findCustomerById(@McpToolParam(description = "The Customer id") Long id) {
        return customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found"));
    }

    @McpTool(description = "Save a new customer")
    public Customer saveCustomer(@McpToolParam(description = "The Customer to save (name, email") Customer customer) {
        return customerRepository.save(customer);
    }
}
