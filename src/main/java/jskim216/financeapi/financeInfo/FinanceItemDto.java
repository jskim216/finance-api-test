package jskim216.financeapi.financeInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinanceItemDto {

    @NotNull
    private String instituteName;
    @NotNull
    private int totalAmount;

}
