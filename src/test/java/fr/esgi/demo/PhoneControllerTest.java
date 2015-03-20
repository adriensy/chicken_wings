package fr.esgi.demo;

import com.jayway.restassured.http.ContentType;
import fr.esgi.demo.domain.Phone;
import fr.esgi.demo.repository.PhoneRepository;
import fr.esgi.demo.web.dto.PhoneDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;


/*@SqlGroup(
        @Sql(
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = ""
        )
        @Sql(
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = ""
        )
)*/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebIntegrationTest
public class PhoneControllerTest {
    @Autowired
    private PhoneRepository phoneRepository;

    @Before
    public void setUp() {


        for (int i = 1; i < 10; i++) {
            Phone phone = new Phone();
            phone.setFirstName("first"+i);
            phone.setSerialNumber("serialNumber"+i);

            int modulo = i%2;

            if (modulo == 0) {
                phone.setStolen(true);
            } else {
                phone.setStolen(false);
            }

            phone.setLastName("last");
            phoneRepository.save(phone);
        }
    }

    @Test
    public void should_ReturnAllPhone() {

        given()
            .log().all()
        .when()
            .get("/phone")
        .then()
            .log().all()
            .statusCode(OK.value());
    }

    @Test
    public void should_ReturnAllStolen() {
        given()
            .log().all()
        .when()
            .get("/phone/stolen")
        .then()
            .log().all()
            .statusCode(OK.value());
    }

    @Test
     public void should_ReturnIsStolen_ById() {
        given()
            .log().all()
        .when()
            .get("/phone/1")
        .then()
            .log().all()
            .statusCode(OK.value());
    }

    @Test
    public void should_UpdateState_BySerialNumber() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{ \"stolen\": false }")
                .when()
                .put("/phone/withSerialNumber/serialNumber2")
                .then()
                .log().all()
                .statusCode(OK.value());
    }

    @Test
     public void should_UpdateState_ById() {
        given()
            .log().all()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body("{ \"stolen\": true }")
        .when()
            .put("/phone/1")
        .then()
            .log().all()
            .statusCode(OK.value());
    }


    @Test
    public void should_CreatePhone() {
        PhoneDto phoneDto = new PhoneDto();

        phoneDto.setFirstName("FirstNameNew");
        phoneDto.setLastName("LastNameNew");
        phoneDto.setSerialNumber("123456");
        phoneDto.setStolen(true);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(phoneDto)
                .when()
                .post("/phone")
                .then()
                .log().all()
                .statusCode(OK.value());
    }
    @Test
    public void should_Delete_ById() {
        given()
            .log().all()
        .when()
            .delete("/phone/1")
        .then()
            .log().all()
            .statusCode(OK.value());
    }

    @Test
    public void ShouldNot_FindThisId() {
        given()
            .log().all()
        .when()
            .get("/phone/100")
        .then()
            .log().all()
            .statusCode(NOT_FOUND.value());
    }
}
