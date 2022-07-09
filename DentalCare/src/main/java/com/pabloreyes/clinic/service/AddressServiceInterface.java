package com.pabloreyes.clinic.service;

import com.pabloreyes.clinic.model.dto.AddressDTO;
import com.pabloreyes.clinic.model.entity.Address;

import java.util.List;

public interface AddressServiceInterface {

    AddressDTO createAddress(Address address);
    AddressDTO readAddressById(Long id);
    List<AddressDTO> readAllAddress();
    AddressDTO updateAddress(Address address);
    AddressDTO deleteAddressById(Long id);

}
