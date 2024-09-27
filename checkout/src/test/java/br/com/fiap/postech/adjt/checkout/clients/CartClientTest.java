package br.com.fiap.postech.adjt.checkout.clients;

import br.com.fiap.postech.adjt.checkout.model.response.CartResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CartClient cartClient;

    private UUID consumerId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        consumerId = UUID.randomUUID();
    }

    @Test
    void shouldReturnCartWhenConsultCart_success() {
        CartResponse mockResponse = new CartResponse();
        mockResponse.setCustomerId(consumerId.toString());

        when(restTemplate.getForEntity(any(String.class), any(Class.class)))
                .thenReturn(ResponseEntity.ok(mockResponse));

        ResponseEntity<CartResponse> response = cartClient.consultCart(consumerId);
        assertEquals(consumerId.toString(), response.getBody().getCustomerId());
    }

    @Test
    void shouldThrowExceptionWhenConsultCartFails() {
        when(restTemplate.getForEntity(any(String.class), any(Class.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        assertThrows(HttpClientErrorException.class, () -> cartClient.consultCart(consumerId));
    }

    @Test
    void shouldClearCartWhenClearCartCalled_success() {
        // Simula a exclusão do carrinho
        cartClient.clearCart(consumerId);
        verify(restTemplate, times(1)).delete(any(String.class));
    }

    @Test
    void shouldThrowExceptionWhenClearCartFails() {
        doThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR))
                .when(restTemplate).delete(any(String.class));

        assertThrows(HttpClientErrorException.class, () -> cartClient.clearCart(consumerId));
    }
}