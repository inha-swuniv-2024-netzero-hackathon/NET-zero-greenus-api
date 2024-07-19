package inha.edu.upcyclingapp.dto;

import inha.edu.upcyclingapp.model.Saving;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SavingDto {
    private String bank;
    private String savingsName;
    private BigDecimal interestRate;
    private BigDecimal balance;

    public SavingDto(Saving saving) {
        this.bank = saving.getBank();
        this.savingsName = saving.getSavingsName();
        this.interestRate = saving.getInterestRate();
        this.balance = saving.getBalance();
    }

    public Saving toEntity(Long userId) {
        return Saving.builder()
                .userId(userId)
                .bank(bank)
                .savingsName(savingsName)
                .interestRate(interestRate)
                .balance(balance)
                .build();
    }
}
