package jskim216.financeapi.codes;

import java.util.Arrays;
import java.util.List;


public enum BankCode {

    bank1("주택도시기금"),
    bank2("국민은행"),
    bank3("우리은행"),
    bank4("신한은행"),
    bank5("한국시티은행"),
    bank6("하나은행"),
    bank7("농협은행/수협은행"),
    bank8("외환은행"),
    bank9("기타은행");


    private final String bankName;

    BankCode(String bankName) {
        this.bankName = bankName;
    }

    public static BankCode getBankCode(String bankName) {

        List<BankCode> bankCodes = Arrays.asList(BankCode.values());
        BankCode code = bankCodes.stream().filter(name -> name.bankName.equals(bankName))
                            .findAny()
                            .orElse(bank9)
        ;

        return code;
    }

    public static String regExrName(String name) {
        return name.replace("(억원)", "");
    }
}
