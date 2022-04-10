

package by.home.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;



@XmlRootElement(namespace="http://www.example.com/bankDeposits", name = "bank-deposits")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankDeposits {
    @XmlElements({

            @XmlElement(name="individual-deposit", type=IndividualDeposit.class),
            @XmlElement(name="legal-entity-deposit", type=LegalEntityDeposit.class)
    })
    private List<BankDeposit> list = new ArrayList<BankDeposit>();
    public BankDeposits() {
        super();
    }
    public void setList(List<BankDeposit> list) {
        this.list = list;
    }
    public boolean add(BankDeposit st) {
        return list.add(st);
    }

    public List<BankDeposit> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "Students [list=" + list + "]";
    }
}
