package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sapientia.furniture.model.FurnitureBunkBed;
import ro.sapientia.furniture.model.FurnitureBunkBedType;
import ro.sapientia.furniture.model.FurnitureMaterial;

import java.util.List;

public interface FurnitureBunkBedRepository  extends JpaRepository<FurnitureBunkBed, Long> {
    FurnitureBunkBed findFurnitureBunkBedById(Long id);

    List<FurnitureBunkBed> findFurnitureBunkBedByMaterial(FurnitureMaterial material);

    List<FurnitureBunkBed> findFurnitureBunkBedByType(FurnitureBunkBedType type);

}
