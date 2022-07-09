package com.pabloreyes.clinic.service.implement;

import com.pabloreyes.clinic.model.dto.AddressDTO;
import com.pabloreyes.clinic.model.entity.Address;
import com.pabloreyes.clinic.repository.AddressRepository;
import com.pabloreyes.clinic.service.AddressServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImplement implements AddressServiceInterface {

    final static Logger LOGGER = Logger.getLogger(AddressServiceImplement.class);
    private AddressRepository addressRepository;
    private ObjectMapper mapper;

    @Autowired
    public AddressServiceImplement(AddressRepository addressRepository, ObjectMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    //----------------------BEGIN. IMPLEMENTED METHODS---------------------------

    @Override
    public AddressDTO createAddress(Address address) {
//        Address address = mapper.convertValue(addressDTO, Address.class);
        LOGGER.info("Start: -createAddress- in <<AddressServiceImplement>>");
        AddressDTO createdAddressDTO = mapper.convertValue(addressRepository.save(address), AddressDTO.class);
        LOGGER.info("Created successfully");

        LOGGER.info("End: -createAddress- in <<AddressServiceImplement>>");
        return createdAddressDTO;
    }

    @Override
    public AddressDTO readAddressById(Long id) {
        LOGGER.info("Start: -readAddressById- in <<AddressServiceImplement>>");
        Optional<Address> address;
        AddressDTO readAddressDTO = new AddressDTO();

        if (id != null){
            address = addressRepository.findById(id);
            if (address.isPresent()){
                readAddressDTO = mapper.convertValue(address, AddressDTO.class);
            }
        }else {
            LOGGER.error("NULL ID -readAddressById- in <<AddressServiceImplement>>");
//            throw new IllegalArgumentException("NULL ID");
        }


        LOGGER.info("End: -readAddressById- in <<AddressServiceImplement>>");
        return readAddressDTO;
    }

    @Override
    public List<AddressDTO> readAllAddress() {
        LOGGER.info("Start: -readAllAddress- in <<AddressServiceImplement>>");
        List<Address> addressList = addressRepository.findAll();
        List<AddressDTO> addressDTOList = new ArrayList<>();

        for (Address address : addressList) {
            addressDTOList.add(mapper.convertValue(address, AddressDTO.class));
        }

        LOGGER.info("End: -readAllAddress- in <<AddressServiceImplement>>");
        return addressDTOList;
    }

    @Override
    public AddressDTO updateAddress(Address address) {
        LOGGER.info("Start: -updateAddress- in <<AddressServiceImplement>>");
        AddressDTO updatedAddressDTO = new AddressDTO();
        AddressDTO addressDTOCheckId = readAddressById(address.getIdAddress());

        if (addressDTOCheckId.getIdAddress() != null){
            updatedAddressDTO = createAddress(address);
            LOGGER.info("Updated successfully");
        }else {
            LOGGER.error("Something went wrong when executing -updateAddress- Not found ID");
        }

        LOGGER.info("End: -updateAddress- in <<AddressServiceImplement>>");
        return updatedAddressDTO;
    }

    @Override
    public AddressDTO deleteAddressById(Long id) {
        LOGGER.info("Start: -deleteAddressById- in <<AddressServiceImplement>>");
        AddressDTO deletedAddressDTO = new AddressDTO();
        AddressDTO addressDTOCheck = readAddressById(id);

        if (addressDTOCheck.getIdAddress() != null){
            deletedAddressDTO = addressDTOCheck;
            addressRepository.deleteById(id);
        }else {
            LOGGER.error("Something went wrong when executing -deleteAddressById- Not found ID");
        }

        LOGGER.info("End: -deleteAddressById- in <<AddressServiceImplement>>");
        return deletedAddressDTO;
    }

    //----------------------END. IMPLEMENTED METHODS---------------------------

}
