package pl.edu.pw.mwo_lab2_crud_shop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.mwo_lab2_crud_shop.dto.entities.ClientDto;
import pl.edu.pw.mwo_lab2_crud_shop.dto.entities.ClientView;
import pl.edu.pw.mwo_lab2_crud_shop.entity.entities.Client;
import pl.edu.pw.mwo_lab2_crud_shop.service.ClientService;

import java.util.Collection;


@RestController
@Slf4j
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Operation(
            summary = "Find all Clients",
            description = "Get all Clients objects.",
            tags = {"get"})
    @GetMapping("/getAll")
    @JsonView(ClientView.Get.class)
    public Collection<ClientDto> findAll() {
        log.info("Looking for all clients");
        return clientService.findAll();
    }

    @Operation(
            summary = "Find a Client by Id",
            description = "Get a Client object by specifying its id.",
            tags = {"get"})
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(schema = @Schema(implementation = ClientDto.class), mediaType = "application/json")})
    @ApiResponse(
            responseCode = "404",
            content = {@Content(schema = @Schema())})
    @ApiResponse(
            responseCode = "500",
            content = {@Content(schema = @Schema())})
    @GetMapping("/{id}")
    @JsonView(value = ClientView.Get.class)
    public ClientDto findById(
            @Parameter(description = "Client Id.", example = "1")
            @PathVariable Long id) {
        log.info("looking for clinet with id {}", id);
        return clientService.findById(id);
    }

    @Operation(
            summary = "Add a Client",
            description = "Create a Client object.",
            tags = {"post"})
    @PostMapping("/addClient")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(value = ClientView.Get.class)
    public ClientDto create(
            @RequestBody @JsonView(value = ClientView.Post.class) ClientDto clientDto) {
        log.info("Creating client: {}", clientDto.toString());
        return clientService.create(clientDto);
    }

    @Operation(
            summary = "Update a Client by Id",
            description = "Update a Client object by specifying its id.",
            tags = {"put"})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = ClientView.Get.class)
    public ClientDto update(
            @Parameter(description = "Client Id.", example = "1")
            @PathVariable Long id,
            @RequestBody @JsonView(value = ClientView.Put.class) ClientDto clientDto) {
        log.info("Update Client with id: {}", id);

        final Client givenClient = Client.builder()
                .clientId(id)
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .email(clientDto.getEmail())
                .build();

        return clientService.update(id, givenClient);
    }

    @Operation(
            summary = "Delete a Client by Id",
            description = "Delete a Client object by specifying its id.",
            tags = {"delete"})
    @DeleteMapping("/{id}")
    public Long deleteEmployee(
            @Parameter(description = "Client Id.", example = "1")
            @PathVariable Long id) {
        log.debug("Delete Client with id: {}", id);
        return clientService.deleteById(id);
    }
}

