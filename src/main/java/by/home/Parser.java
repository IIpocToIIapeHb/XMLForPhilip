package by.home;

import by.home.entity.BankDeposit;

import java.util.List;

public interface Parser {
    List<BankDeposit> parse(String file) throws SaxParserException;
}
