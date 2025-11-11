package com.niladri.catalogue_service.web.controllers;

import com.niladri.catalogue_service.AbstractIt;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
class CatalogueControllerTest extends AbstractIt {

    @Test
    void shouldReturnProducts() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/core")
                .then()
                .statusCode(200)
                .body("data", Matchers.hasSize(5))
                .body("totalElements", Matchers.is(15))
                .body("totalPages", Matchers.is(3))
                .body("pageNumber", Matchers.is(1));
    }

    @Test
    void shouldGetProductByCode() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/core/P100")
                .then()
                .statusCode(200)
                .body("code", Matchers.is("P100"))
                .body("name", Matchers.is("The Hunger Games"))
                .body("price", Matchers.is(34.00f));
    }

    @Test
    void shouldReturn404ForNonExistingProductCode() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/core/NON_EXISTING_CODE")
                .then()
                .statusCode(404);
    }
}
