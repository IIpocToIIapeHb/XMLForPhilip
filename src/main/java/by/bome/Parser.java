package by.bome;

import by.bome.entity.BankDeposit;

import java.util.List;

public interface Parser {
    List<BankDeposit> parse(String file);
}
