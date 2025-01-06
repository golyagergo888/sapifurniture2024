package ro.sapientia.furniture.service;

import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.FurnitureBunkBed;
import ro.sapientia.furniture.repository.FurnitureBunkBedRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FurnitureBunkBedService {

    private final FurnitureBunkBedRepository furnitureBunkBedRepository;

    public FurnitureBunkBedService(final FurnitureBunkBedRepository furnitureBunkBedRepository) {
        this.furnitureBunkBedRepository = furnitureBunkBedRepository;
    }

    public List<FurnitureBunkBed> findAllFurnitureBunkBeds() {
        return furnitureBunkBedRepository.findAll();
    }

    public FurnitureBunkBed findFurnitureBunkBedById(Long id) {
        return furnitureBunkBedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bunk bed not found with id: " + id));
    }

    public ArrayList<FurnitureBunkBed> findAllFurnitureBunkBedsByType(String type) {
        return furnitureBunkBedRepository.findFurnitureBunkBedByType(type);
    }

    public ArrayList<FurnitureBunkBed> findAllFurnitureBunkBedsByMaterial(String material) {
        return furnitureBunkBedRepository.findFurnitureBunkBedByMaterial(material);
    }

    public FurnitureBunkBed saveFurnitureBunkBed(FurnitureBunkBed furnitureBunkBed) {
        return this.furnitureBunkBedRepository.save(furnitureBunkBed);
    }

    public void deleteFurnitureBunkBedById(Long id) {
        if (!furnitureBunkBedRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete bunk bed with id: " + id + ", it does not exist.");
        }
        furnitureBunkBedRepository.deleteById(id);
    }
}
