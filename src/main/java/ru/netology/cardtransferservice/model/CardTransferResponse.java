package ru.netology.cardtransferservice.model;

import java.time.LocalDateTime;

public class CardTransferResponse {
    private String status;
    private double commission;
    private LocalDateTime timestamp;

    public CardTransferResponse(String status, double commission, LocalDateTime timestamp) {
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
