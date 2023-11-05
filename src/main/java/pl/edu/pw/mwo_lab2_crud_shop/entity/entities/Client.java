package pl.edu.pw.mwo_lab2_crud_shop.entity.entities;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Client {
    long clientId;
    String firstName;
    String lastName;
    String email;
}
