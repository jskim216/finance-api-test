package jskim216.financeapi.financeInfo;

import jskim216.financeapi.banks.Bank;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Finance {

    @Id @GeneratedValue
    private int id;
    private int year;
    private int month;
    private int amount;
    @ManyToOne(targetEntity = Bank.class)
    @JoinColumn(name = "institute_code")
    private Bank instituteCode;
}
