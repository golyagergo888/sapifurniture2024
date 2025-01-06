package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sapientia.furniture.model.FurnitureBunkBed;

import java.util.ArrayList;

public interface FurnitureBunkBedRepository  extends JpaRepository<FurnitureBunkBed, Long> {
    FurnitureBunkBed findFurnitureBunkBedById(Long id);

    ArrayList<FurnitureBunkBed> findFurnitureBunkBedByType(String type);

    ArrayList<FurnitureBunkBed> findFurnitureBunkBedByMaterial(String material);

}
