package com.zzootec.customer.service;

import com.zzootec.customer.dto.CustomerRequest;
import com.zzootec.customer.dto.CustomerResponse;
import com.zzootec.customer.model.Customer;

import java.util.List;

public interface ICustomerService {

    List<CustomerResponse> findAllCustomers();
    CustomerResponse findById(Long id);
    Long createCustomer(CustomerRequest customerRequest);
    void updateCustomer(CustomerRequest customerRequest);
    void mergeCustomer(Customer customer, CustomerRequest customerRequest);
    boolean existsById(Long id);
    void deleteCustomer(Long id);

}
