package fr.esgi.demo;

import fr.esgi.demo.web.dto.GameDto;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.OK;


public class GameControllerTest {
    @Test
    public void should_GetOneGame() {

        GameDto gameDto = new GameDto();

        given()
            .log().all()
        .when()
            .get("/game")
        .then()
            .log().all()
            .statusCode(OK.value());
    }
}
