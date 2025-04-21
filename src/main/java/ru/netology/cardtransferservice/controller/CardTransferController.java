package ru.netology.cardtransferservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.cardtransferservice.model.CardTransferRequest;
import ru.netology.cardtransferservice.model.CardTransferResponse;
import ru.netology.cardtransferservice.model.ConfirmationData;
import ru.netology.cardtransferservice.model.OperationIdResponse;
import ru.netology.cardtransferservice.service.CardTransferService;

@RestController
@RequiredArgsConstructor
public class CardTransferController {
    private final CardTransferService cardTransferService;

    @PostMapping("/transfer")
    public ResponseEntity<CardTransferResponse> transfer(@RequestBody CardTransferRequest request) {
        CardTransferResponse response = cardTransferService.transfer(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/confirmOperation")
    public ResponseEntity<OperationIdResponse> confirm(@RequestBody ConfirmationData confirmationData) {
        String operationId = cardTransferService.confirm(confirmationData);
        return ResponseEntity.ok(new OperationIdResponse(operationId));
    }
}
