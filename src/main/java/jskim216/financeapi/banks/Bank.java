package jskim216.financeapi.banks;


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
public class Bank {

    @Id @GeneratedValue
    private int id;
    private String instituteCode;
    private String instituteName;

}
