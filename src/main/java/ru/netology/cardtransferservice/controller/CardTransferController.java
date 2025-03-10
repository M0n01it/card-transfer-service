package ru.netology.cardtransferservice.controller;

import ru.netology.cardtransferservice.model.CardTransferRequest;
import ru.netology.cardtransferservice.model.CardTransferResponse;
import ru.netology.cardtransferservice.service.CardTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transfer")
public class CardTransferController {

    private final CardTransferService cardTransferService;

    @Autowired
    public CardTransferController(CardTransferService cardTransferService) {
        this.cardTransferService = cardTransferService;
    }

    @PostMapping
    public ResponseEntity<CardTransferResponse> transferMoney(@RequestBody CardTransferRequest request) {
        CardTransferResponse response = cardTransferService.transfer(request);
        return ResponseEntity.ok(response);
    }
}
