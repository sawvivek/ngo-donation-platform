package com.ngo.ngoplatform.controller;

import com.ngo.ngoplatform.entity.Ngo;
import com.ngo.ngoplatform.service.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ngos")
public class NgoController {

    @Autowired
    private NgoService ngoService;

    @PostMapping
    public Ngo createNgo(@RequestBody Ngo ngo) {
        return ngoService.saveNgo(ngo);
    }

    @GetMapping
    public List<Ngo> getAllNgos() {
        return ngoService.getAllNgos();
    }

    @GetMapping("/{id}")
    public Optional<Ngo> getNgoById(@PathVariable Long id) {
        return ngoService.getNgoById(id);
    }

    @PutMapping("/{id}")
    public Ngo updateNgo(@PathVariable Long id,
                         @RequestBody Ngo ngo) {
        return ngoService.updateNgo(id, ngo);
    }

    @DeleteMapping("/{id}")
    public String deleteNgo(@PathVariable Long id) {
        ngoService.deleteNgo(id);
        return "NGO Deleted Successfully";
    }

}