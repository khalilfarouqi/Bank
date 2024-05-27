package com.bank.bankservice.controller;

import com.bank.bankservice.common.dtos.CustomerDto;
import com.bank.bankservice.common.dtos.Request.*;
import com.bank.bankservice.common.dtos.Response.*;
import com.bank.bankservice.services.Implimentation.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class CustomerGraphqlController {
    private final CustomerServiceImpl customerServiceImpl;
    public CustomerGraphqlController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }
    @QueryMapping
    List<CustomerDto> customers() {
        return customerServiceImpl.getAllCustomers();
    }
    @QueryMapping
    CustomerDto customerByIdentity(@Argument String identity) {
        return customerServiceImpl.getCustomByIdentity(identity);
    }
    @MutationMapping
    public AddCustomerResponse createCustomer(@Argument("dto") AddCustomerRequest dto) {
        return customerServiceImpl.createCustomer(dto);
    }
    @MutationMapping
    public AddCustomerResponse signUpCustomer(@Argument("dto") AddCustomerRequest dto) {
        return customerServiceImpl.signUpCustomer(dto);
    }
    @MutationMapping
    public UpdateCustomerResponse updateCustomer(@Argument("identityRef") String identityRef, @Argument("dto")
    UpdateCustomerRequest dto) {
        return customerServiceImpl.updateCustomer(identityRef, dto);
    }
    @MutationMapping
    public String deleteCustomer(@Argument("identityRef") String identityRef) {
        return customerServiceImpl.deleteCustomerByIdentityRef(identityRef);
    }
}
