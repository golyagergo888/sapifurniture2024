package ro.sapientia.furniture.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ro.sapientia.furniture.model.FurnitureBunkBed;
import ro.sapientia.furniture.model.FurnitureMaterial;
import ro.sapientia.furniture.service.FurnitureBunkBedService;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FurnitureBunkBedController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class FurnitureBunkBedControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean(FurnitureBunkBedService.class)
    private FurnitureBunkBedService furnitureBodyService;

    @Test
    public void testFindAllFurnitureBunkBeds() throws Exception {
        var bunkBed = new FurnitureBunkBed();
        bunkBed.setMaterial(FurnitureMaterial.WOOD);

        when(furnitureBodyService.findAllFurnitureBunkBeds()).thenReturn(List.of(bunkBed));

        this.mockMvc.perform(get("/furniture/bunk-bed")).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].material", is(FurnitureMaterial.WOOD.toString())));
    }

    @Test
    public void testFindFurnitureBunkBedById() throws Exception {
        var bunkBed = new FurnitureBunkBed();
        bunkBed.setId(1L);
        bunkBed.setMaterial(FurnitureMaterial.METAL);

        when(furnitureBodyService.findFurnitureBunkBedById(1L)).thenReturn(bunkBed);

        this.mockMvc.perform(get("/furniture/bunk-bed/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.material", is(FurnitureMaterial.METAL.toString())))
                .andExpect(jsonPath("$.id", is(1)));
    }
}
