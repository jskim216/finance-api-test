package jskim216.financeapi.banks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankDto {

    @NotNull
    private String instituteCode;
    @NotNull
    private String instituteName;
}
