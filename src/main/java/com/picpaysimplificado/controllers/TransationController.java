package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.transation.Transation;
import com.picpaysimplificado.dtos.TransationDTO;
import com.picpaysimplificado.services.TransationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transations")
public class TransationController {
    @Autowired
    private TransationService transationService;

    @PostMapping
    public ResponseEntity<Transation> createTransation(@RequestBody TransationDTO transationDTO) throws Exception {
        Transation transation = transationService.createTransation(transationDTO);
        return new ResponseEntity<>(transation, HttpStatus.CREATED);
    }
}
