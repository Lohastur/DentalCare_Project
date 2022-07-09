package com.pabloreyes.clinic.controller;


import com.pabloreyes.clinic.model.dto.TurnDTO;
import com.pabloreyes.clinic.model.entity.Turn;
import com.pabloreyes.clinic.service.implement.TurnServiceImplement;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turn")
public class TurnController {

    private TurnServiceImplement turnService;

    @Autowired
    public TurnController(TurnServiceImplement turnService) {
        this.turnService = turnService;
    }


    @PostMapping("/create")
    @ApiOperation("Request to create a new Turn")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request: AUTOINCREMENT ID / NULL DENTIST / NULL PATIENT")
    })
    public ResponseEntity<?> createTurn(@RequestBody Turn turn){
        ResponseEntity response = new ResponseEntity<>("AUTOINCREMENT ID", HttpStatus.BAD_REQUEST);
        TurnDTO turnDTOResponse = new TurnDTO();

        if (turn.getIdTurn() == null){
            response = new ResponseEntity<>("NULL PATIENT / NULL DENTIST", HttpStatus.BAD_REQUEST);
            turnDTOResponse = turnService.createTurn(turn);

            if (turnDTOResponse.getIdTurn() != null){
                response = new ResponseEntity<>(turnDTOResponse, HttpStatus.CREATED);
            }
        }


        return response;
    }

    @GetMapping("/read/{id}")
    @ApiOperation("Request to read a Turn by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> readTurn(@PathVariable("id") Long id){
        ResponseEntity response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        TurnDTO turnDTOResponse = turnService.readTurnById(id);

        if (turnDTOResponse.getIdTurn() != null){
            response = new ResponseEntity<>(turnDTOResponse, HttpStatus.OK);
        }

        return response;
    }

    @GetMapping("/read/all")
    @ApiOperation("Request to read All Turns")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<?> readAllTurn(){
        return new ResponseEntity<>(turnService.readAllTurn(), HttpStatus.OK);
    }

    @GetMapping("/read/all/dentist/{id}")
    @ApiOperation("Request to read All Turns by Dentist id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<?> readAllTurnByDentist(@PathVariable("id") Long id){
        return new ResponseEntity<>(turnService.readAllTurnByDentistId(id), HttpStatus.OK);
    }

    @GetMapping("/read/all/patient/{id}")
    @ApiOperation("Request to read All Turns by Patient id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<?> readAllTurnByPatient(@PathVariable("id") Long id){
        return new ResponseEntity<>(turnService.readAllTurnByPatientId(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    @ApiOperation("Request to update a Turn")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request: NULL DENTIST / NULL PATIENT"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> updateTurn(@RequestBody Turn turn){
        ResponseEntity response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        TurnDTO turnDTOResponse = turnService.updateTurn(turn);

        if (turn.getIdTurn() != null && turnDTOResponse.getIdTurn() != null){
            response = new ResponseEntity<>("Bad Request: NULL DENTIST / NULL PATIENT", HttpStatus.BAD_REQUEST);

            if (turnDTOResponse.getDentist() != null && turnDTOResponse.getPatient() != null){

                response = new ResponseEntity<>(turnService.updateTurn(turn), HttpStatus.OK);
            }
        }

        return response;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Request to delete a Turn by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> deleteTurn(@PathVariable("id") Long id){
        ResponseEntity response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        TurnDTO turnDTOResponse = turnService.deleteTurnById(id);

        if (turnDTOResponse.getIdTurn() != null){
            response = new ResponseEntity<>(turnDTOResponse, HttpStatus.OK);
        }

        return response;

    }
}
