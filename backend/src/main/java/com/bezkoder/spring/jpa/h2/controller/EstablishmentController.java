package com.bezkoder.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jpa.h2.model.Establishment;
import com.bezkoder.spring.jpa.h2.model.User;
import com.bezkoder.spring.jpa.h2.repository.EstablishmentRepository;
import com.bezkoder.spring.jpa.h2.services.MapboxService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EstablishmentController {

    @Autowired
    EstablishmentRepository estRepository;

    @Autowired
    private MapboxService mapboxService;

    //   Establishment Register
    @PostMapping("/api/establishment")
    public String createEstablishment(@RequestBody Establishment establishment) {
        try {
            estRepository.save(new Establishment(establishment.getUser(), establishment.getTitle(),
                    establishment.getDescription(), establishment.getAddress(), establishment.getImage(),
                    establishment.getOpen(), establishment.getClose()));
            return "REGISTER_SUCCESS";

        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString();
        }
    }
    
    //   Establishment Update
    @PutMapping("/api/establishment/{estId}")
    public String updateEstablishment(@PathVariable("estId") long id, @RequestBody Establishment establishment) {
        System.out.println(" --------- " + id);
        System.out.println(" --------- " + establishment);
        Optional<Establishment> estData = estRepository.findById(id);

        try {
            Establishment _est = estData.get();

            _est.setTitle(establishment.getTitle());
            _est.setDescription(establishment.getDescription());
            _est.setAddress(establishment.getAddress());
            _est.setOpen(establishment.getOpen());
            _est.setClose(establishment.getClose());

            estRepository.save(_est);

            return "REGISTER_SUCCESS";

        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString();
        }
    }
    
    // Fetch Establishment 
    @GetMapping("/api/establishment/{id}")
    public ResponseEntity<Establishment> getEstablishmentById(@PathVariable("id") long id) {
        Optional<Establishment> estData = estRepository.findById(id);
        if (estData.isPresent()) {
            return new ResponseEntity<>(estData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Search Establishment 
    @GetMapping("/api/establishments/{key}")
    public ResponseEntity<Establishment> getEstablishmentByKey(@PathVariable("key") String query) {
        List<Establishment> ests = estRepository.findByTitleContainingIgnoreCase(query);
        if (ests.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ests.get(0), HttpStatus.OK);
        }
        // return mapboxService.findAddress(query);
    }

    // Applied Establishments
    @GetMapping("/api/applied_establishments/{user}")
    public ResponseEntity<List<Establishment>> getAppliedEstablishmentByUser(@PathVariable("user") String user) {
        List<Establishment> estData = estRepository.findByUserid((user));

        if (estData.size() != 0) {
            return new ResponseEntity<>(estData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/establishment/{id}")
    public String deleteEstablishment(@PathVariable("id") long id) {
        try {
            estRepository.deleteById(id);
            return "DELETE_SUCCESS";
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR).toString();
        }
    }



}
