package br.com.bb.t99;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@Transactional
public class PetTest {
    @Test
    public void testPetGetEndpoint() {
        given().contentType("application/json")
                .when().post("/pet")
                .then().statusCode(200)
                .and().body("mensagem", not(equalTo(null)));
    }

    @Test
    public void testPetGetIdEndpoint() {
        int codigoConta = 101;

        given().pathParam("id", codigoConta)
                .when().get("/pet/{id}")
                .then().statusCode(200)
                .and().body("codigoConta", equalTo(codigoConta));
    }
}
