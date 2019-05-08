package jskim216.financeapi.financeInfo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private String instituteCode;
}
