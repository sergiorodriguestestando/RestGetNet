package br.com.testes;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

import br.com.core.BaseTest;

import static io.restassured.RestAssured.given;



public class GetNetTest extends BaseTest {
	
	

	
	
	@Test
	public void ValidarMetGetSingle() {

		given()
		.when()
			.get("/users/3")
		.then()
			.statusCode(200)
			.body("data.id", is(3))
			.body("data.first_name", containsString("Emma"))
			.body("data.last_name", containsString("Wong"))
			;

	}
	
	
	@Test
	public void ValidarListUsers() {

		given()
		.when()
			.get("https://reqres.in/api/unknown")
		.then()
			.statusCode(200)
			.body(is(not(nullValue())))
			.body("data.id", hasSize(6));

		
	}
	
	
	@Test
	public void ValidarDeletarUsuarioTest() {
		given()
		.log().all()
		.when()
			.delete("/users/2")
		.then()
		.log().all()
		.statusCode(204)
		
		   ;
		
		
	}
	
	@Test
	public void ValidarAlterarUsuarioTest() {
		
		
	
		
		given()
		.log().all()
		.contentType("application/json")
		.body("{\"email\": \"sergio.bluth@reqres.in\",\"first_name\": \"sergio\",\"last_name\": \"rodrigues\",	\"avatar\": \"https://reqres.in/img/faces/1-image.jpg\"}")

		
		
		.when()
			.put("/api/users/3")
		.then()
		.log().all()
		.statusCode(200)
		  
			.body("first_name", is("sergio"))
		
		   ;
		
		
	}

}
