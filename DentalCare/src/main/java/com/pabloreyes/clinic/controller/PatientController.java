package com.pabloreyes.clinic.controller;



import com.pabloreyes.clinic.model.dto.PatientDTO;
import com.pabloreyes.clinic.model.entity.Patient;
import com.pabloreyes.clinic.service.implement.PatientServiceImplement;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private PatientServiceImplement patientService;

    @Autowired
    public PatientController(PatientServiceImplement patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/create")
    @ApiOperation("Request to create a new Patient")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request: AUTOINCREMENT ID / NULL DNI or ALREADY EXIST DNI")
    })
    public ResponseEntity<?> createPatient(@RequestBody Patient patient){
        ResponseEntity response = new ResponseEntity<>("AUTOINCREMENT ID",HttpStatus.BAD_REQUEST);
        PatientDTO patientDTOResponse = new PatientDTO();

        if (patient.getIdPatient() == null){
            response = new ResponseEntity<>("NULL DNI or ALREADY EXIST DNI",HttpStatus.BAD_REQUEST);
            patientDTOResponse = patientService.createPatient(patient);

            if (patientDTOResponse.getIdPatient() != null){

                response = new ResponseEntity<>(patientDTOResponse, HttpStatus.CREATED);
            }
        }


        return response;
    }


    @GetMapping("/read/{id}")
    @ApiOperation("Request to read a Patient by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> readPatient(@PathVariable("id") Long id){
        ResponseEntity response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        PatientDTO patientDTOResponse = patientService.readPatientById(id);

        if (patientDTOResponse.getIdPatient() != null){
            response = new ResponseEntity<>(patientDTOResponse, HttpStatus.OK);
        }

        return response;
    }

    @GetMapping("/read/dni/{dni}")
    @ApiOperation("Request to read a Patient by dni")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> readPatientDni(@PathVariable("dni") Long dni){
        ResponseEntity response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        PatientDTO patientDTOResponse = patientService.readPatientByDni(dni);

        if (patientDTOResponse.getIdPatient() != null){
            response = new ResponseEntity<>(patientDTOResponse, HttpStatus.OK);
        }

        return response;
    }

    @GetMapping("/read/all")
    @ApiOperation("Request to read All Patients")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<?> readAllPatient(){
        return new ResponseEntity<>(patientService.readAllPatient(), HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation("Request to update a Patient")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request: NULL DNI or ALREADY EXIST DNI"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> updatePatient(@RequestBody Patient patient){
        ResponseEntity response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        PatientDTO patientDTOResponse = patientService.updatePatient(patient);

        if (patientDTOResponse.getIdPatient() != null){
            response = new ResponseEntity<>("NULL DNI or ALREADY EXIST DNI", HttpStatus.BAD_REQUEST);

            if (patientDTOResponse.getDni() != null){

                response = new ResponseEntity<>(patientDTOResponse, HttpStatus.OK);
            }
        }

        return response;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Request to delete a Patient by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> deletePatient(@PathVariable("id") Long id){
        ResponseEntity response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        PatientDTO patientDTOResponse = patientService.deletePatientById(id);


        if (patientDTOResponse.getIdPatient() != null){
            response = new ResponseEntity<>(patientDTOResponse, HttpStatus.OK);
        }

        return response;
    }

}
