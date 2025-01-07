package ro.sapientia.furniture.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ro.sapientia.furniture.dto.FurnitureBunkBedDTO;
import ro.sapientia.furniture.model.FurnitureBunkBed;
import ro.sapientia.furniture.model.FurnitureBunkBedType;
import ro.sapientia.furniture.model.FurnitureMaterial;
import ro.sapientia.furniture.repository.FurnitureBunkBedRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class FurnitureBunkBedComponentTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FurnitureBunkBedRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void testAddFurnitureBunkBed() throws Exception{
        var bunkBed = new FurnitureBunkBed();
        bunkBed.setId(1L);
        bunkBed.setMaterial(FurnitureMaterial.WOOD);
        bunkBed.setType(FurnitureBunkBedType.TWO_LEVEL);
        bunkBed.setDepth(100);
        bunkBed.setWidth(150);
        bunkBed.setHeight(10);

        FurnitureBunkBedDTO bunkBedDTO = new FurnitureBunkBedDTO();
        bunkBedDTO.setMaterial(FurnitureMaterial.WOOD);
        bunkBedDTO.setType(FurnitureBunkBedType.TWO_LEVEL);
        bunkBedDTO.setDepth(bunkBed.getDepth());
        bunkBedDTO.setWidth(bunkBed.getWidth());
        bunkBedDTO.setHeight(bunkBed.getHeight());

        ObjectMapper objectMapper = new ObjectMapper();
        String bunkBedDTOJson = objectMapper.writeValueAsString(bunkBedDTO);

        this.mockMvc.perform(post("/furniture/bunk-bed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bunkBedDTOJson))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.depth", is(bunkBed.getDepth())))
                        .andExpect(jsonPath("$.width", is(bunkBed.getWidth())))
                        .andExpect(jsonPath("$.height", is(bunkBed.getHeight())))
                        .andExpect(jsonPath("$.type", is(bunkBed.getType().toString())))
                        .andExpect(jsonPath("$.material", is(bunkBed.getMaterial().toString())));
    }

    @Test
    public void testFindAllTables() throws Exception {

        var bunkBed1 = new FurnitureBunkBed();
        bunkBed1.setMaterial(FurnitureMaterial.WOOD);
        bunkBed1.setType(FurnitureBunkBedType.TWO_LEVEL);
        bunkBed1.setDepth(100);
        bunkBed1.setWidth(150);
        bunkBed1.setHeight(10);

        repository.save(bunkBed1);

        FurnitureBunkBed bunkBed2 = new FurnitureBunkBed();
        bunkBed2.setMaterial(FurnitureMaterial.METAL);
        bunkBed2.setType(FurnitureBunkBedType.THREE_LEVEL);
        bunkBed2.setDepth(120);
        bunkBed2.setWidth(180);
        bunkBed2.setHeight(15);

        repository.save(bunkBed2);

        this.mockMvc.perform(get("/furniture/bunk-bed")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].material", is(bunkBed1.getMaterial().toString())))
                .andExpect(jsonPath("$[0].type", is(bunkBed1.getType().toString())))
                .andExpect(jsonPath("$[0].depth", is(bunkBed1.getDepth())))
                .andExpect(jsonPath("$[0].width", is(bunkBed1.getWidth())))
                .andExpect(jsonPath("$[0].height", is(bunkBed1.getHeight())))
                .andExpect(jsonPath("$[1].material", is(bunkBed2.getMaterial().toString())))
                .andExpect(jsonPath("$[1].type", is(bunkBed2.getType().toString())))
                .andExpect(jsonPath("$[1].depth", is(bunkBed2.getDepth())))
                .andExpect(jsonPath("$[1].width", is(bunkBed2.getWidth())))
                .andExpect(jsonPath("$[1].height", is(bunkBed2.getHeight())));

    }
}
