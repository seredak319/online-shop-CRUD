package pl.edu.pw.mwo_lab2_crud_shop.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pw.mwo_lab2_crud_shop.dto.entities.client.ClientDto;
import pl.edu.pw.mwo_lab2_crud_shop.entity.entities.Client;
import pl.edu.pw.mwo_lab2_crud_shop.mapper.entities.client.ClientConverter;
import pl.edu.pw.mwo_lab2_crud_shop.mapper.entities.client.ClientMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClientServiceTest {

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private ClientConverter clientConverter;

    @InjectMocks
    private ClientService clientService;

    @Test
    void findAll_ShouldReturnListOfClients() {
        // Arrange
        List<Client> mockClients = Collections.singletonList(Client.builder().build());
        List<ClientDto> expectedClientDtos = Collections.singletonList(ClientDto.builder().build());

        when(clientMapper.findAllClients()).thenReturn(mockClients);
        when(clientConverter.toDto(any())).thenReturn(ClientDto.builder().build());

        // Act
        List<ClientDto> result = clientService.findAll();

        // Assert
        assertEquals(expectedClientDtos, result);
        verify(clientMapper, times(1)).findAllClients();
        verify(clientConverter, times(1)).toDto(any());
    }

    @Test
    void findById_ShouldReturnClientDto() {
        // Arrange
        Long clientId = 1L;
        Client mockClient = Client.builder().build();
        ClientDto expectedClientDto = ClientDto.builder().build();

        when(clientMapper.fetchClient(clientId)).thenReturn(mockClient);
        when(clientConverter.toDto(mockClient)).thenReturn(expectedClientDto);

        // Act
        ClientDto result = clientService.findById(clientId);

        // Assert
        assertEquals(expectedClientDto, result);
        verify(clientMapper, times(1)).fetchClient(clientId);
        verify(clientConverter, times(1)).toDto(mockClient);
    }

    @Test
    void create_ShouldReturnCreatedClientDto() {
        // Arrange
        ClientDto clientDtoToCreate = ClientDto.builder().build();

        // Act
        ClientDto result = clientService.create(clientDtoToCreate);

        // Assert
        assertEquals(clientDtoToCreate, result);
        verify(clientMapper, times(1)).addClient(any());
        verify(clientConverter, times(1)).toClient(clientDtoToCreate);
    }

    @Test
    void update_ShouldReturnUpdatedClientDto() {
        // Arrange
        Long clientId = 1L;
        Client updatedClient = Client.builder().build();
        ClientDto expectedUpdatedClientDto = ClientDto.builder().build();

        when(clientConverter.toDto(updatedClient)).thenReturn(expectedUpdatedClientDto);

        // Act
        ClientDto result = clientService.update(clientId, updatedClient);

        // Assert
        assertEquals(expectedUpdatedClientDto, result);
        verify(clientMapper, times(1)).updateClient(clientId, updatedClient);
        verify(clientConverter, times(1)).toDto(updatedClient);
    }

    @Test
    void deleteById_ShouldReturnDeletedClientId() {
        // Arrange
        Long clientIdToDelete = 1L;

        // Act
        Long result = clientService.deleteById(clientIdToDelete);

        // Assert
        assertEquals(clientIdToDelete, result);
        verify(clientMapper, times(1)).deleteClientById(clientIdToDelete);
    }
}

