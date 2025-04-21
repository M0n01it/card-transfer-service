package ru.netology.cardtransferservice.repository;

import org.springframework.stereotype.Repository;
import ru.netology.cardtransferservice.model.Card;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CardRepository {
    private final Map<String, Card> cards = new ConcurrentHashMap<>();

    public CardRepository() {
        // Инициализация карт по умолчанию
        cards.put("1111111111111111", new Card("1111111111111111", "12/24", "111", 10000));
        cards.put("2222222222222222", new Card("2222222222222222", "12/24", "222", 10000));
    }

    public Card getCardByNumber(String cardNumber) {
        return cards.get(cardNumber);
    }
} 