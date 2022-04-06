import entity.BankDeposit;

import java.util.List;

public interface Parser {
    List<BankDeposit> parse(String file);
}
