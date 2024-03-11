package com.bank.bankservice.Services;

import com.bank.bankservice.common.dtos.CustomerDto;
import com.bank.bankservice.common.dtos.Request.*;
import com.bank.bankservice.common.dtos.Response.*;

public interface ICustomerService extends IService<CustomerDto> {
    AddCustomerResponse createCustomer(AddCustomerRequest addCustomerRequest);
    UpdateCustomerResponse updateCustomer(String identityRef, UpdateCustomerRequest updateCustomerRequest);
    CustomerDto getCustomByIdentity(String identity);
    String deleteCustomerByIdentityRef(String identityRef);
}
