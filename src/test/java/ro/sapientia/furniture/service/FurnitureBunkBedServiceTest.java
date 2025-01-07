package ro.sapientia.furniture.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.sapientia.furniture.model.FurnitureBunkBed;
import ro.sapientia.furniture.model.FurnitureBunkBedType;
import ro.sapientia.furniture.model.FurnitureMaterial;
import ro.sapientia.furniture.repository.FurnitureBunkBedRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FurnitureBunkBedServiceTest {
    private FurnitureBunkBedRepository repositoryMock;

    private FurnitureBunkBedService service;

    @BeforeEach
    public void setUp() {
        repositoryMock = mock(FurnitureBunkBedRepository.class);
        service = new FurnitureBunkBedService(repositoryMock);
    }

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

        FurnitureBunkBed bunkBed3 = new FurnitureBunkBed();

        bunkBed3.setDepth(300);
        bunkBed3.setHeight(350);
        bunkBed3.setWidth(30);
        bunkBed3.setMaterial(FurnitureMaterial.METAL);
        bunkBed3.setType(FurnitureBunkBedType.THREE_LEVEL);

        when(repositoryMock.findAll()).thenReturn(List.of(bunkBed1, bunkBed2, bunkBed3));
        var result = service.findAllFurnitureBunkBeds();

        assert result != null;
        assert result.size() == 3;
    }

    @Test
    public void testFindFurnitureBunkBedById() {
        FurnitureBunkBed bunkBed = new FurnitureBunkBed();
        bunkBed.setId(1L);
        bunkBed.setDepth(100);
        bunkBed.setHeight(150);
        bunkBed.setWidth(10);
        bunkBed.setMaterial(FurnitureMaterial.WOOD);
        bunkBed.setType(FurnitureBunkBedType.THREE_LEVEL);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(bunkBed));
        FurnitureBunkBed result = service.findFurnitureBunkBedById(1L);

        assert result != null;
        assert result.getId() == 1L;
        assert result.getMaterial() == FurnitureMaterial.WOOD;
        assert result.getType() == FurnitureBunkBedType.THREE_LEVEL;
        assert result.getDepth() == 100;
        assert result.getHeight() == 150;
        assert result.getWidth() == 10;
    }

    @Test
    public void testFindFurnitureBunkBedById_NotFound() {
        when(repositoryMock.findById(99L)).thenReturn(Optional.empty());
        FurnitureBunkBed result = service.findFurnitureBunkBedById(99L);

        assert result == null;
    }
}
