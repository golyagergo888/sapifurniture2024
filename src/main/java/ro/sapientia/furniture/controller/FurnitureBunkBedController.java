package ro.sapientia.furniture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sapientia.furniture.model.FurnitureBunkBed;
import ro.sapientia.furniture.service.FurnitureBunkBedService;

import java.util.List;

@RestController
@RequestMapping("/furnitures/bunk-beds")
public class FurnitureBunkBedController {

    private final FurnitureBunkBedService bunkBedService;

    @Autowired
    public FurnitureBunkBedController(FurnitureBunkBedService bunkBedService) {
        this.bunkBedService = bunkBedService;
    }

    // Get all bunk beds
    @GetMapping
    public ResponseEntity<List<FurnitureBunkBed>> getAllBunkBeds() {
        List<FurnitureBunkBed> bunkBeds = bunkBedService.findAllFurnitureBunkBeds();
        return new ResponseEntity<>(bunkBeds, HttpStatus.OK);
    }

    // Get a single bunk bed by ID
    @GetMapping("/{id}")
    public ResponseEntity<FurnitureBunkBed> getBunkBedById(@PathVariable Long id) {
        FurnitureBunkBed bunkBed = bunkBedService.findFurnitureBunkBedById(id);
        return new ResponseEntity<>(bunkBed, HttpStatus.OK);
    }

    // Create a new bunk bed
    @PostMapping
    public ResponseEntity<FurnitureBunkBed> createBunkBed(@RequestBody FurnitureBunkBed bunkBed) {
        FurnitureBunkBed savedBunkBed = bunkBedService.saveFurnitureBunkBed(bunkBed);
        return new ResponseEntity<>(savedBunkBed, HttpStatus.CREATED);
    }

    // Update an existing bunk bed
    @PutMapping("/{id}")
    public ResponseEntity<FurnitureBunkBed> updateBunkBed(@PathVariable Long id, @RequestBody FurnitureBunkBed bunkBed) {
        bunkBed.setId(id);
        FurnitureBunkBed updatedBunkBed = bunkBedService.saveFurnitureBunkBed(bunkBed);
        return new ResponseEntity<>(updatedBunkBed, HttpStatus.OK);
    }

    // Delete a bunk bed by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBunkBed(@PathVariable Long id) {
        bunkBedService.deleteFurnitureBunkBedById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Find bunk beds by material
    @GetMapping("/material/{material}")
    public ResponseEntity<List<FurnitureBunkBed>> getBunkBedsByMaterial(@PathVariable String material) {
        List<FurnitureBunkBed> bunkBeds = bunkBedService.findAllFurnitureBunkBedsByMaterial(material);
        return new ResponseEntity<>(bunkBeds, HttpStatus.OK);
    }

    // Find bunk beds by type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<FurnitureBunkBed>> getBunkBedsByType(@PathVariable String type) {
        List<FurnitureBunkBed> bunkBeds = bunkBedService.findAllFurnitureBunkBedsByType(type);
        return new ResponseEntity<>(bunkBeds, HttpStatus.OK);
    }
}