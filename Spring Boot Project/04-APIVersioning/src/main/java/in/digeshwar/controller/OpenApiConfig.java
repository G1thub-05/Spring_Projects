package in.digeshwar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Users", description = "User management APIs")
@RestController
@RequestMapping("/api/v1/users")

class UserController {
	@Value("${api.v2.users-url}")
	private String usersV2Url;

	@Operation(
			summary = "Get users - V1",
			description = """
                This API is deprecated. Please migrate to /api/v2/users.
                V1 will be retired on Sunset date 31 December 2027.
                """
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Successful response"),
			@ApiResponse(responseCode = "404", description = "User not found")
	})

	@GetMapping("/{id}")
	@Deprecated
	public ResponseEntity<String> getUsers(
			@Parameter(description = "Unique ID of the user", example = "07")
			@PathVariable("id") Long id) {

		boolean exists = true;
		if (!exists) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + id + " not found");}
		return ResponseEntity
				.ok()
				.header("Deprecation", "true")
				.header("Sunset", "Wed, 31 Dec 2027 23:59:59 GMT")
				.header("Link", "<" + usersV2Url + ">; rel=\"successor-version\"")
				.body("Hi Arun with id " + id + " retrieved successfully");
	}
	@Operation(
			summary = "Create a new user",
			description = "Creates a new user in the system"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "User created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid user data")
	})
	@PostMapping
	public ResponseEntity<String> createUser() {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("User created successfully");
	}
}




@Configuration
public class OpenApiConfig {

	@Bean
	public GroupedOpenApi usersV1Api() {
		return GroupedOpenApi.builder()
				.group("Users-v1")          //👈 Defines the group name
				.pathsToMatch("/api/v1/**") //👈 /v3/api-docs/users-v1
				.build();                   //👈 Creates the GroupedOpenApi configuration.
	}

	@Bean
	public GroupedOpenApi usersV2Api() {
		return GroupedOpenApi.builder()
				.group("Users-v2")          //👈 Defines the group name
				.pathsToMatch("/api/v2/**") //👈 Includes only V1 endpoints in this group
				.build();                   //👈 Creates the GroupedOpenApi configuration.
	}
}