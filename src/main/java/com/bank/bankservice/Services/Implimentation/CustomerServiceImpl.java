package com.bank.bankservice.Services.Implimentation;

import com.bank.bankservice.Repository.CustomerRepository;
import com.bank.bankservice.Services.ICustomerService;
import com.bank.bankservice.common.dtos.CustomerDto;
import com.bank.bankservice.common.dtos.Request.AddCustomerRequest;
import com.bank.bankservice.common.dtos.Request.UpdateCustomerRequest;
import com.bank.bankservice.common.dtos.Response.AddCustomerResponse;
import com.bank.bankservice.common.dtos.Response.UpdateCustomerResponse;
import com.bank.bankservice.entity.Customer;
import com.bank.bankservice.exception.BusinessException;
import jakarta.transaction.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class)).
                collect(Collectors.toList());    }
    @Override
    public AddCustomerResponse createCustomer(AddCustomerRequest addCustomerRequest) {
        Customer customer = modelMapper.map(addCustomerRequest, Customer.class);
        String identityRef = customer.getIdentityRef();
        String username = customer.getUserName();
        customerRepository.findByIdentityRef(identityRef)
                .ifPresent(a ->{
                    throw new BusinessException(String.format("Customer with the same identity [%s] exist", identityRef));
                });
        customerRepository.findByUserName(username)
                .ifPresent(a ->{
                    throw new BusinessException(String.format("The username [%s] is already used", username));
                });
        AddCustomerResponse response = modelMapper.map(customerRepository.save(customer), AddCustomerResponse.class);
        response.setMessage(String.format("Customer : [identity= %s,First Name= %s, Last Name= %s, username= %s] was created with success",
                response.getIdentityRef(), response.getFirstName(), response.getLastName(), response.getUserName()));
        return response;
    }

    @Override
    public UpdateCustomerResponse updateCustomer(String identityRef, UpdateCustomerRequest updateCustomerRequest) {
        Customer customerToPersist = modelMapper.map(updateCustomerRequest, Customer.class);
        Customer customerFound = customerRepository.findAll().stream().filter(bo ->
                bo.getIdentityRef().equals(identityRef)).findFirst().orElseThrow(
                () -> new BusinessException(String.format("No Customer with identity [%s] exist !", identityRef))
        );
        customerToPersist.setId(customerFound.getId());
        customerToPersist.setIdentityRef(identityRef);
        UpdateCustomerResponse updateCustomerResponse = modelMapper.map(customerRepository.save(customerToPersist), UpdateCustomerResponse.class);
        updateCustomerResponse.setMessage(String.format("Customer identity %s is updated with success", identityRef));
        return updateCustomerResponse;
    }

    @Override
    public CustomerDto getCustomByIdentity(String identity) {
        return modelMapper
                .map(customerRepository.findByIdentityRef(identity)
                        .orElseThrow(() -> new BusinessException(String.format("No Customer with identity [%s] exist !", identity))),
                        CustomerDto.class);
    }

    @Override
    public String deleteCustomerByIdentityRef(String identityRef) {
        if (identityRef == null || identityRef.isEmpty())
            throw new BusinessException("Enter a correct identity customer");
        Customer customerFound = customerRepository.findAll().stream()
                .filter(customer ->
                    customer.getIdentityRef().equals(identityRef)).findFirst().orElseThrow(
                        () -> new BusinessException(String.format("No customer with identity %s exist in database",
                                identityRef))
        );
        customerRepository.delete(customerFound);
        return String.format("Customer with identity %s is deleted with success", identityRef);
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getById(Long id) {
        return modelMapper.map(customerRepository.findById(id), CustomerDto.class);
    }

    @Override
    public CustomerDto save(CustomerDto dto) {
        Customer customer = modelMapper.map(dto, Customer.class);
        String identityRef = customer.getIdentityRef();
        String username = customer.getUserName();
        customerRepository.findByIdentityRef(identityRef)
                .ifPresent(a ->{
                    throw new BusinessException(String.format("Customer with the same identity [%s] exist", identityRef));
                });
        customerRepository.findByUserName(username)
                .ifPresent(a ->{
                    throw new BusinessException(String.format("The username [%s] is already used", username));
                });
        return modelMapper.map(customerRepository.save(customer), CustomerDto.class);
    }

    @Override
    public CustomerDto update(CustomerDto dto) {
        Customer customer = modelMapper.map(dto, Customer.class);
        String identityRef = customer.getIdentityRef();
        String username = customer.getUserName();
        customerRepository.findByIdentityRef(identityRef)
                .ifPresent(a ->{
                    throw new BusinessException(String.format("Customer with the same identity [%s] exist", identityRef));
                });
        customerRepository.findByUserName(username)
                .ifPresent(a ->{
                    throw new BusinessException(String.format("The username [%s] is already used", username));
                });
        return modelMapper.map(customerRepository.save(customer), CustomerDto.class);
    }

    @Override
    public String deleteById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return String.format("Customer with identity %s is deleted with success", customer.get().getIdentityRef());
        }
        return "Customer was not found";
    }
}
