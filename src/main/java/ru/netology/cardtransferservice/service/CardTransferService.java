package ru.netology.cardtransferservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.cardtransferservice.exception.ConfirmationException;
import ru.netology.cardtransferservice.exception.TransferException;
import ru.netology.cardtransferservice.model.*;
import ru.netology.cardtransferservice.repository.CardRepository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class CardTransferService {
    private final CardRepository cardRepository;
    
    // Потокобезопасное хранилище для операций, ожидающих подтверждения
    private final Map<String, CardTransferRequest> pendingTransfers = new ConcurrentHashMap<>();
    private final Map<String, String> confirmationCodes = new ConcurrentHashMap<>();

    public CardTransferResponse transfer(CardTransferRequest request) {
        Card cardFrom = cardRepository.getCardByNumber(request.getFromCardNumber());
        Card cardTo = cardRepository.getCardByNumber(request.getToCardNumber());

        if (cardFrom == null || cardTo == null) {
            throw new TransferException("Карта не найдена");
        }

        if (cardFrom.getBalance() < request.getAmount()) {
            throw new TransferException("Недостаточно средств");
        }

        // Генерируем уникальный идентификатор операции
        String operationId = UUID.randomUUID().toString();
        
        // Сохраняем данные о переводе и код подтверждения
        pendingTransfers.put(operationId, request);
        confirmationCodes.put(operationId, "0000"); // Для простоты всегда используем код 0000
        
        return new CardTransferResponse(operationId);
    }
    
    public String transfer(TransferData transferData) {
        Card cardFrom = cardRepository.getCardByNumber(transferData.getCardFromNumber());
        Card cardTo = cardRepository.getCardByNumber(transferData.getCardToNumber());

        if (cardFrom == null || cardTo == null) {
            throw new TransferException("Карта не найдена");
        }

        if (!cardFrom.getCvv().equals(transferData.getCardFromCVV())) {
            throw new TransferException("Неверный CVV код");
        }

        if (cardFrom.getBalance() < transferData.getAmount().getValue()) {
            throw new TransferException("Недостаточно средств");
        }

        // Генерируем уникальный идентификатор операции
        String operationId = UUID.randomUUID().toString();
        
        // Сохраняем данные о переводе и код подтверждения
        pendingTransfers.put(operationId, new CardTransferRequest(
            transferData.getCardFromNumber(),
            transferData.getCardToNumber(),
            transferData.getAmount().getValue()
        ));
        confirmationCodes.put(operationId, "0000"); // Для простоты всегда используем код 0000
        
        return operationId;
    }
    
    public String confirm(ConfirmationData confirmationData) {
        String operationId = confirmationData.getOperationId();
        String code = confirmationData.getCode();
        
        // Проверяем существование операции
        if (!pendingTransfers.containsKey(operationId)) {
            throw new ConfirmationException("Операция не найдена");
        }
        
        // Проверяем код подтверждения
        if (!confirmationCodes.get(operationId).equals(code)) {
            throw new ConfirmationException("Неверный код подтверждения");
        }
        
        // Получаем данные о переводе
        CardTransferRequest request = pendingTransfers.get(operationId);
        
        // Выполняем перевод
        Card cardFrom = cardRepository.getCardByNumber(request.getFromCardNumber());
        Card cardTo = cardRepository.getCardByNumber(request.getToCardNumber());
        
        cardFrom.setBalance(cardFrom.getBalance() - request.getAmount());
        cardTo.setBalance(cardTo.getBalance() + request.getAmount());
        
        // Удаляем данные об операции из хранилища
        pendingTransfers.remove(operationId);
        confirmationCodes.remove(operationId);
        
        return operationId;
    }
}
