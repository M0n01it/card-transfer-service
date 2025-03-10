package ru.netology.cardtransferservice.service;

import ru.netology.cardtransferservice.model.CardTransferRequest;
import ru.netology.cardtransferservice.model.CardTransferResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CardTransferService {

    private static final double COMMISSION_RATE = 0.02; // 2% комиссия

    public CardTransferResponse transfer(CardTransferRequest request) {
        // Вычисление комиссии
        double commission = request.getAmount() * COMMISSION_RATE;

        // Имитация перевода (в реальности сопровождалось бы сохранением в БД и взаимодействием с платежной системой)
        boolean success = Math.random() > 0.1; // Условие успешности операции (90%)

        // Запись перевода в логи
        logTransaction(request, commission, success);

        // Возвращаем результат
        return new CardTransferResponse(
                success ? "SUCCESS" : "FAILED",
                commission,
                LocalDateTime.now()
        );
    }

    private void logTransaction(CardTransferRequest request, double commission, boolean success) {
        // Простой лог в консоль, можно заменить на запись в файл или БД
        System.out.printf("[%s] Transfer from %s to %s, amount: %.2f, commission: %.2f, status: %s%n",
                LocalDateTime.now(),
                request.getFromCardNumber(),
                request.getToCardNumber(),
                request.getAmount(),
                commission,
                success ? "SUCCESS" : "FAILED"
        );
    }
}
