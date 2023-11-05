package pl.edu.pw.mwo_lab2_crud_shop.dto.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString
public class ClientDto {
    @JsonView({ClientView.Get.class, ClientView.Post.class, ClientView.Put.class})
    String firstName;
    @JsonView({ClientView.Get.class, ClientView.Post.class, ClientView.Put.class})
    String lastName;
    @JsonView({ClientView.Get.class, ClientView.Post.class, ClientView.Put.class})
    String email;
}
