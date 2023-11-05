package pl.edu.pw.mwo_lab2_crud_shop.mapper.entities.client;

import org.apache.ibatis.annotations.Mapper;
import pl.edu.pw.mwo_lab2_crud_shop.entity.entities.Client;

import java.util.List;

@Mapper
public interface ClientMapper {

    Client fetchClient(Long clientId);

    void deleteClientById(Long clientId);

    void addClient(Client client);

    void updateClient(Long clientId, Client client);

    List<Client> findAllClients();
}