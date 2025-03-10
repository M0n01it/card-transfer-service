package ru.netology.cardtransferservice;

import org.springframework.boot.SpringApplication;

public class TestCardTransferServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(CardTransferServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
