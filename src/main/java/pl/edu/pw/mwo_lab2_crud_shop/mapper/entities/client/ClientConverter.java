package pl.edu.pw.mwo_lab2_crud_shop.mapper.entities.client;

import org.springframework.stereotype.Component;
import pl.edu.pw.mwo_lab2_crud_shop.dto.entities.client.ClientDto;
import pl.edu.pw.mwo_lab2_crud_shop.entity.entities.Client;

@Component
public class ClientConverter {

    public ClientDto toDto(Client client) {
        return ClientDto.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .build();
    }

    public Client toClient(ClientDto clientDto) {
        return Client.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .email(clientDto.getEmail())
                .build();
    }
}
