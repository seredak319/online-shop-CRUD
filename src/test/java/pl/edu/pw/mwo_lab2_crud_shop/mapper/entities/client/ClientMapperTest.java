package pl.edu.pw.mwo_lab2_crud_shop.mapper.entities.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import pl.edu.pw.mwo_lab2_crud_shop.entity.entities.Client;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
class ClientMapperTest {

    @Autowired
    private ClientMapper objectUnderTests;

    @Test
    void testFetchClient() {

        //given
        Long clientId = 1L;

        //when
        Client client = objectUnderTests.fetchClient(clientId);

        //then
        assertThat(client).isNotNull();
    }
}
