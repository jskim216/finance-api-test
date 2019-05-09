package jskim216.financeapi.financeInfo;

import jskim216.financeapi.banks.Bank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinanceDto {

    @NotNull
    private int year;
    @NotNull
    private int month;
    @NotNull
    private Bank instituteCode;
    @NotNull
    private int amount;
}
