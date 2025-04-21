package ru.netology.cardtransferservice.model;

public class CardTransferRequest {
    private String fromCardNumber;
    private String toCardNumber;
    private double amount;

    // Конструктор по умолчанию
    public CardTransferRequest() {
    }

    // Конструктор с параметрами
    public CardTransferRequest(String fromCardNumber, String toCardNumber, double amount) {
        this.fromCardNumber = fromCardNumber;
        this.toCardNumber = toCardNumber;
        this.amount = amount;
    }

    public String getFromCardNumber() {
        return fromCardNumber;
    }

    public void setFromCardNumber(String fromCardNumber) {
        this.fromCardNumber = fromCardNumber;
    }

    public String getToCardNumber() {
        return toCardNumber;
    }

    public void setToCardNumber(String toCardNumber) {
        this.toCardNumber = toCardNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
