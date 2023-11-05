package pl.edu.pw.mwo_lab2_crud_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.mwo_lab2_crud_shop.dto.entities.ClientDto;
import pl.edu.pw.mwo_lab2_crud_shop.entity.entities.Client;
import pl.edu.pw.mwo_lab2_crud_shop.mapper.entities.ClientConverter;
import pl.edu.pw.mwo_lab2_crud_shop.mapper.entities.client.ClientMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ClientService {

    // todo walidacje i try catche

    private final ClientMapper clientMapper;
    private final ClientConverter clientConverter;

    public List<ClientDto> findAll() {
        return clientMapper.findAllClients().stream()
                .map(clientConverter::toDto)
                .toList();
    }

    public ClientDto findById(Long id) {
        return clientConverter.toDto(clientMapper.fetchClient(id));
    }

    public ClientDto create(ClientDto clientDto) {
        clientMapper.addClient(clientConverter.toClient(clientDto));
        return clientDto;
    }

    public ClientDto update(Long id, Client client) {
        clientMapper.updateClient(id, client);
        return clientConverter.toDto(client);
    }

    public Long deleteById(Long id) {
        clientMapper.deleteClientById(id);
        return id;
    }
}
