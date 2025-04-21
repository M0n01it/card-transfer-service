package ru.netology.cardtransferservice.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardTransferResponse {
    private String operationId;
    private String status;
    private double commission;
    private LocalDateTime timestamp;

    // Конструктор только с operationId
    public CardTransferResponse(String operationId) {
        this.operationId = operationId;
        this.status = "SUCCESS";
        this.commission = 0.0;
        this.timestamp = LocalDateTime.now();
    }
    
    // Полный конструктор
    public CardTransferResponse(String operationId, String status, double commission, LocalDateTime timestamp) {
        this.operationId = operationId;
        this.status = status;
        this.commission = commission;
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public double getCommission() {
        return commission;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
