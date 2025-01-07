package ro.sapientia.furniture.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import ro.sapientia.furniture.model.FurnitureBunkBed;
import ro.sapientia.furniture.model.FurnitureBunkBedType;
import ro.sapientia.furniture.model.FurnitureMaterial;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class FurnitureBunkBedRepositoryTest {
    @Autowired
    FurnitureBunkBedRepository furnitureBunkBedRepository;

    @Test
    public void testFindAllFurnitureBunkBeds() {
        FurnitureBunkBed bunkBed1 = new FurnitureBunkBed();
        bunkBed1.setDepth(100);
        bunkBed1.setHeight(150);
        bunkBed1.setWidth(10);
        bunkBed1.setMaterial(FurnitureMaterial.WOOD);
        bunkBed1.setType(FurnitureBunkBedType.THREE_LEVEL);

        FurnitureBunkBed bunkBed2 = new FurnitureBunkBed();
        bunkBed2.setDepth(200);
        bunkBed2.setHeight(250);
        bunkBed2.setWidth(20);
        bunkBed2.setMaterial(FurnitureMaterial.METAL);
        bunkBed2.setType(FurnitureBunkBedType.TWO_LEVEL);

        this.furnitureBunkBedRepository.save(bunkBed1);
        this.furnitureBunkBedRepository.save(bunkBed2);

        var result = this.furnitureBunkBedRepository.findAll();

        Assertions.assertEquals(result.size(), 2);
    }

    @Test
    public void testFindAllByMaterial(){
        FurnitureBunkBed bunkBed1 = new FurnitureBunkBed();
        bunkBed1.setDepth(100);
        bunkBed1.setHeight(150);
        bunkBed1.setWidth(10);
        bunkBed1.setMaterial(FurnitureMaterial.WOOD);
        bunkBed1.setType(FurnitureBunkBedType.THREE_LEVEL);

        FurnitureBunkBed bunkBed2 = new FurnitureBunkBed();
        bunkBed2.setDepth(200);
        bunkBed2.setHeight(250);
        bunkBed2.setWidth(20);
        bunkBed2.setMaterial(FurnitureMaterial.METAL);
        bunkBed2.setType(FurnitureBunkBedType.TWO_LEVEL);

        this.furnitureBunkBedRepository.save(bunkBed1);
        this.furnitureBunkBedRepository.save(bunkBed2);

        var result = this.furnitureBunkBedRepository.findFurnitureBunkBedByMaterial(FurnitureMaterial.WOOD);

        Assertions.assertEquals(result.size(), 1);
    }
}
