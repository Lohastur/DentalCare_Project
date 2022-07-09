package com.pabloreyes.clinic.controller;


import com.pabloreyes.clinic.model.dto.DentistDTO;
import com.pabloreyes.clinic.model.entity.Dentist;
import com.pabloreyes.clinic.service.implement.DentistServiceImplement;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    private DentistServiceImplement dentistService;

    @Autowired
    public DentistController(DentistServiceImplement dentistService) {
        this.dentistService = dentistService;
    }

    @PostMapping("/create")
    @ApiOperation("Request to create a new Dentist")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request: AUTOINCREMENT ID / NULL LICENCE or ALREADY EXIST LICENCE")
    })
    public ResponseEntity<?> createDentist(@RequestBody Dentist dentist){
        ResponseEntity response = new ResponseEntity<>("AUTOINCREMENT ID", HttpStatus.BAD_REQUEST);
        DentistDTO dentistDTOResponse = new DentistDTO();

        if (dentist.getIdDentist() == null){
            response = new ResponseEntity<>("NULL LICENCE or ALREADY EXIST LICENCE", HttpStatus.BAD_REQUEST);
            dentistDTOResponse = dentistService.createDentist(dentist);

            if (dentistDTOResponse.getIdDentist() != null){

                response = new ResponseEntity<>(dentistDTOResponse, HttpStatus.CREATED);
            }
        }

        return response;
    }

    @GetMapping("/read/{id}")
    @ApiOperation("Request to read a Dentist by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> readDentist(@PathVariable("id") Long id){
        ResponseEntity response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        DentistDTO dentistDTOResponse = dentistService.readDentistById(id);

        if (dentistDTOResponse.getIdDentist() != null){
            response = new ResponseEntity<>(dentistDTOResponse, HttpStatus.OK);
        }

        return response;
    }

    @GetMapping("/read/all")
    @ApiOperation("Request to read All Dentists")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<?> readAllDentist(){
        return new ResponseEntity<>(dentistService.readAllDentist(), HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation("Request to update a Dentist")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request: NULL LICENCE or ALREADY EXIST LICENCE"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> updateDentist(@RequestBody Dentist dentist){
        ResponseEntity response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        DentistDTO dentistDTOResponse = dentistService.updateDentist(dentist);

        if (dentistDTOResponse.getIdDentist() != null){
            response = new ResponseEntity<>("NULL LICENCE or ALREADY EXIST LICENCE", HttpStatus.BAD_REQUEST);

            if (dentistDTOResponse.getLicensePlate() != null){

                response = new ResponseEntity<>(dentistDTOResponse, HttpStatus.OK);
            }
        }

        return response;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Request to delete a Dentist by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> deleteDentist(@PathVariable("id") Long id){
        ResponseEntity response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        DentistDTO dentistDTOResponse = dentistService.deleteDentistById(id);

        if (dentistDTOResponse.getIdDentist() != null){
            response = new ResponseEntity<>(dentistDTOResponse, HttpStatus.OK);
        }

        return response;
    }
}
