package com.zzootec.customer.service.impl;

import com.zzootec.customer.dto.CustomerRequest;
import com.zzootec.customer.dto.CustomerResponse;
import com.zzootec.customer.exception.CustomerNotFoundException;
import com.zzootec.customer.mapper.CustomerMapper;
import com.zzootec.customer.model.Customer;
import com.zzootec.customer.repository.CustomerRepository;
import com.zzootec.customer.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Override
    public List<CustomerResponse> findAllCustomers() {
        return this.customerRepository.findAll()
                .stream()
                .map(this.customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse findById(Long id) {
        return this.customerRepository.findById(id)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id %s not found", id)));
    }

    @Override
    public Long createCustomer(CustomerRequest customerRequest) {
        Customer customer = this.customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest customerRequest) {
        Customer customer = this.customerRepository.findById(customerRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                  String.format("Cannont update customer:: No customer found with the provided ID: %s", customerRequest.id())
                ));

        mergeCustomer(customer, customerRequest);
        this.customerRepository.save(customer);
    }

    @Override
    public void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customerRequest.firstName())) {
            customer.setFirstName(customerRequest.firstName());
        }
        if (StringUtils.isNotBlank(customerRequest.lastName())) {
            customer.setLastName(customerRequest.lastName());
        }
        if (StringUtils.isNotBlank(customerRequest.email())) {

        }
    }

    @Override
    public boolean existsById(Long id) {
        return this.customerRepository.findById(id)
                .isPresent();
    }

    @Override
    public void deleteCustomer(Long id) {
        this.customerRepository.deleteById(id);
    }
}
