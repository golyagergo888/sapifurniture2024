package ro.sapientia.furniture.service;

import org.springframework.stereotype.Service;
import ro.sapientia.furniture.dto.FurnitureBunkBedDTO;
import ro.sapientia.furniture.model.FurnitureBunkBed;
import ro.sapientia.furniture.model.FurnitureBunkBedType;
import ro.sapientia.furniture.model.FurnitureMaterial;
import ro.sapientia.furniture.repository.FurnitureBunkBedRepository;

import java.util.List;

@Service
public class FurnitureBunkBedService {

    private final FurnitureBunkBedRepository furnitureBunkBedRepository;

    public FurnitureBunkBedService(final FurnitureBunkBedRepository furnitureBunkBedRepository) {
        this.furnitureBunkBedRepository = furnitureBunkBedRepository;
    }

    public FurnitureBunkBed createBunkBed(FurnitureBunkBedDTO dto) {
        FurnitureBunkBed bunkBed = new FurnitureBunkBed(
                dto.getWidth(),
                dto.getHeight(),
                dto.getDepth(),
                dto.getMaterial(),
                dto.getType()
        );
        return furnitureBunkBedRepository.save(bunkBed);
    }

    public FurnitureBunkBed updateFurnitureBunkBed(Long id, FurnitureBunkBedDTO dto) {
        FurnitureBunkBed existingBunkBed = findFurnitureBunkBedById(id);
        existingBunkBed.setWidth(dto.getWidth());
        existingBunkBed.setHeight(dto.getHeight());
        existingBunkBed.setDepth(dto.getDepth());
        existingBunkBed.setMaterial(dto.getMaterial());
        existingBunkBed.setType(dto.getType());
        return saveFurnitureBunkBed(existingBunkBed);
    }

    public List<FurnitureBunkBed> findAllFurnitureBunkBeds() {
        return furnitureBunkBedRepository.findAll();
    }

    public FurnitureBunkBed findFurnitureBunkBedById(final Long id) {
        return furnitureBunkBedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bunk bed not found with id: " + id));
    }

    public List<FurnitureBunkBed> findAllFurnitureBunkBedsByMaterial(FurnitureMaterial material) {
        return furnitureBunkBedRepository.findFurnitureBunkBedByMaterial(material);
    }

    public List<FurnitureBunkBed> findAllFurnitureBunkBedsByType(FurnitureBunkBedType type) {
        return furnitureBunkBedRepository.findFurnitureBunkBedByType(type);
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
