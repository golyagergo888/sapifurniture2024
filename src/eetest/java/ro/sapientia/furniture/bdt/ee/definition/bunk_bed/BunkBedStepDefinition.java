package ro.sapientia.furniture.bdt.ee.definition.bunk_bed;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import ro.sapientia.furniture.dto.FurnitureBunkBedDTO;
import ro.sapientia.furniture.model.FurnitureBunkBed;
import ro.sapientia.furniture.model.FurnitureBunkBedType;
import ro.sapientia.furniture.model.FurnitureMaterial;

import java.util.List;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@TestPropertySource(locations = "classpath:eetest.properties")
@ContextConfiguration
public class BunkBedStepDefinition {
    @Autowired
    private TestEntityManager entityManager;

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    @Given("^that we have the following furniture bunk beds:$")
    public void that_we_have_the_following_furniture_bunk_beds(final DataTable bunkBedTable) throws Throwable {
        for (final Map<String, String> data : bunkBedTable.asMaps(String.class, String.class)) {
            FurnitureBunkBed bunkBed = new FurnitureBunkBed();
            bunkBed.setHeight(Integer.parseInt(data.get("height")));
            bunkBed.setWidth(Integer.parseInt(data.get("width")));
            bunkBed.setDepth(Integer.parseInt(data.get("depth")));
            bunkBed.setMaterial(FurnitureMaterial.valueOf(data.get("material")));
            bunkBed.setType(FurnitureBunkBedType.valueOf(data.get("type")));

            entityManager.persist(bunkBed);
        }
        entityManager.flush();
    }

    @When("^I invoke the furniture bunk bed all endpoint$")
    public void I_invoke_the_furniture_bunk_bed_all_endpoint() throws Throwable {

    }

    @Then("^I should get the material \"([^\"]*)\" for the last bunk bed in the response$")
    public void I_should_get_result_in_stories_list(final String material) throws Throwable {
        List<FurnitureBunkBed> bunkBedList = webClient.get()
                .uri("/furniture/bunk-bed")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<FurnitureBunkBed>>() {})
                .block();

        assert bunkBedList != null;
        assert !bunkBedList.isEmpty();
        assert bunkBedList.get(bunkBedList.size() - 1).getMaterial().toString().equals(material);
    }

    @After
    public void close() {
    }
}
