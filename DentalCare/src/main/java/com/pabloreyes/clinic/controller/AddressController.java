package com.pabloreyes.clinic.controller;



import com.pabloreyes.clinic.model.dto.AddressDTO;
import com.pabloreyes.clinic.model.entity.Address;
import com.pabloreyes.clinic.service.implement.AddressServiceImplement;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressServiceImplement addressService;

    @Autowired
    public AddressController(AddressServiceImplement addressService) {
        this.addressService = addressService;
    }



    @PostMapping("/create")
    @ApiOperation("Request to create a new Address")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request: AUTOINCREMENT ID")
    })
    public ResponseEntity<?> createAddress(@RequestBody Address address){
        ResponseEntity response = new ResponseEntity<>("AUTOINCREMENT ID", HttpStatus.BAD_REQUEST);
        AddressDTO addressDTOResponse = new AddressDTO();

        if (address.getIdAddress() == null){
            addressDTOResponse = addressService.createAddress(address);

            if (addressDTOResponse.getIdAddress() != null){

                response = new ResponseEntity<>(addressDTOResponse, HttpStatus.CREATED);
            }
        }

        return response;
    }

    @GetMapping("/read/{id}")
    @ApiOperation("Request to read an Address by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> readAddress(@PathVariable("id") Long id){
        ResponseEntity response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        AddressDTO addressDTOResponse = addressService.readAddressById(id);

        if(addressDTOResponse.getIdAddress() != null){
            response = new ResponseEntity<>(addressDTOResponse,HttpStatus.OK);
        }

        return response;
    }

    @GetMapping("/read/all")
    @ApiOperation("Request to read All Addresses")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<?> readAllAddress(){
        return new ResponseEntity<>(addressService.readAllAddress(), HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation("Request to update an Address")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> updateAddress(@RequestBody Address address){
        ResponseEntity response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        AddressDTO addressDTOResponse = addressService.updateAddress(address);

        if (addressDTOResponse.getIdAddress() != null){
            response = new ResponseEntity<>(addressDTOResponse, HttpStatus.OK);
        }

        return response;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Request to delete an Address by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> deleteAddress(@PathVariable("id") Long id){
        ResponseEntity response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        AddressDTO addressDTOResponse = addressService.deleteAddressById(id);

        if (addressDTOResponse.getIdAddress() != null){
            response = new ResponseEntity<>(addressDTOResponse, HttpStatus.OK);
        }

        return response;
    }


}
