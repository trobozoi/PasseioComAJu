package br.com.bb.t99;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.google.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;

import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
@Transactional
public class AgendaDaoTest {
/*
    @PersistenceContext
    EntityManager em;

    @Inject
    LancamentoDao lancamentoDao;

    @Test
    public void testContasEndpoint() {
        assertSucessAndContaOnList("/contas", 101);
    }

    @Test
    public void testContasPostEndpoint() {
        int codigoConta = 105;

        given().body("{\"nomeCliente\": \"João Teste\", \"codigoConta\": " + codigoConta + "}")
                .contentType("application/json")
                .when().post("/contas")
                .then().statusCode(201)
                .and().body("mensagem", not(equalTo(null)));
    }

    @Test
    public void testContasIdEndpoint() {
        int codigoConta = 101;

        given().pathParam("id", codigoConta)
                .when().get("/contas/{id}")
                .then().statusCode(200)
                .and().body("codigoConta", equalTo(codigoConta));
    }

    @Test
    public void testErrorContasIdEndpoint() {
        int codigoConta = 99999;

        given().pathParam("id", codigoConta)
                .when().get("/contas/{id}")
                .then().statusCode(422);
    }

    @Test
    public void testContasIdPutEndpoint() {
        int codigoConta = 101;

        given().pathParam("id", codigoConta)
                .when().put("/contas/{id}")
                .then().statusCode(200);

        given().pathParam("id", codigoConta)
                .when().get("/contas/{id}")
                .then().body("estadoConta", equalTo(true));
    }

    @Test
    public void testErrorContasIdPutEndpoint() {
        int codigoConta = 9999;

        given().pathParam("id", codigoConta)
                .when().put("/contas/{id}")
                .then().statusCode(422);
    }

    @Test
    public void testInativasEndpoint() {
        assertSucessAndContaOnList("/contas/inativas", 102);
    }

    private void assertSucessAndContaOnList(String endpoint, int codigoConta) {
        List<Map<String, Object>> contas = given().get(endpoint)
                .then().statusCode(200)
                .extract().as(new TypeRef<List<Map<String, Object>>>() {
                });
        assertThat(contas, hasItems(hasEntry(equalTo("codigoConta"), equalTo(codigoConta))));
    }

    @Test
    public void testNameInLogin() {
        given()
                .pathParam("id", 201)
                .when().get("/contas/login/{id}")
                .then()
                .statusCode(200)
                .body("nomeCliente", is("Wilson Maria da Silva de Alencar"));
    }

    @Test
    public void testErrorInLogin() {
        given()
                .pathParam("id", 99999)
                .when().get("/contas/login/{id}")
                .then()
                .statusCode(422);
    }

    @Test
    public void testLoginConta() throws ErroSqlException {
        ContaDao contaDao = new ContaDao(em);
        Conta conta = contaDao.loginConta(5);
        assertNotNull(conta);
    }

    @Test
    public void testSolicitaLimite() throws ErroSqlException {
        ContaDao contaDao = new ContaDao(em);
        Conta conta = contaDao.loginConta(5);
        contaDao.solicitaLimite(conta);
        assertNotNull(conta);
    }

    @Test
    public void testBuscaContas() throws ErroSqlException {
        ContaDao contaDao = new ContaDao(em);
        List<Conta> contas = contaDao.buscaContas();
        assertNotEquals(new ArrayList<>(), contas);
    }
    */
}
