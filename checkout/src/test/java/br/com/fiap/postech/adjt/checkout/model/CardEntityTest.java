package br.com.fiap.postech.adjt.checkout.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

class CardEntityTest {

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        UUID consumerId = UUID.randomUUID();
        String number = "1234567890123456";
        String expirationMonth = "12";
        String expirationYear = "2025";
        String cvv = "123";
        String name = "John Doe";
        CardEntity cardEntity = new CardEntity(id, consumerId, number, expirationMonth, expirationYear, cvv, name);
        assertEquals(id, cardEntity.getId());
        assertEquals(consumerId, cardEntity.getConsumerId());
        assertEquals(number, cardEntity.getNumber());
        assertEquals(expirationMonth, cardEntity.getExpiration_month());
        assertEquals(expirationYear, cardEntity.getExpiration_year());
        assertEquals(cvv, cardEntity.getCvv());
        assertEquals(name, cardEntity.getName());
    }

    @Test
    void testNoArgsConstructor() {
        CardEntity cardEntity = new CardEntity();
        assertNull(cardEntity.getId());
        assertNull(cardEntity.getConsumerId());
        assertNull(cardEntity.getNumber());
        assertNull(cardEntity.getExpiration_month());
        assertNull(cardEntity.getExpiration_year());
        assertNull(cardEntity.getCvv());
        assertNull(cardEntity.getName());
    }

    @Test
    void testSettersAndGetters() {
        CardEntity cardEntity = new CardEntity();
        UUID id = UUID.randomUUID();
        UUID consumerId = UUID.randomUUID();
        String number = "1234567890123456";
        String expirationMonth = "12";
        String expirationYear = "2025";
        String cvv = "123";
        String name = "John Doe";
        cardEntity.setId(id);
        cardEntity.setConsumerId(consumerId);
        cardEntity.setNumber(number);
        cardEntity.setExpiration_month(expirationMonth);
        cardEntity.setExpiration_year(expirationYear);
        cardEntity.setCvv(cvv);
        cardEntity.setName(name);
        assertEquals(id, cardEntity.getId());
        assertEquals(consumerId, cardEntity.getConsumerId());
        assertEquals(number, cardEntity.getNumber());
        assertEquals(expirationMonth, cardEntity.getExpiration_month());
        assertEquals(expirationYear, cardEntity.getExpiration_year());
        assertEquals(cvv, cardEntity.getCvv());
        assertEquals(name, cardEntity.getName());
    }
}