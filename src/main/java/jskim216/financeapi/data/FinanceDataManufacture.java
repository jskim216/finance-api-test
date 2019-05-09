package jskim216.financeapi.data;

import jskim216.financeapi.Codes.BankCode;
import jskim216.financeapi.banks.Bank;
import jskim216.financeapi.banks.BankDto;
import jskim216.financeapi.banks.BankRepository;
import jskim216.financeapi.financeInfo.Finance;
import jskim216.financeapi.financeInfo.FinanceDto;
import jskim216.financeapi.financeInfo.FinanceRepository;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class FinanceDataManufacture {

    private final ModelMapper modelMapper;
    private FinanceRepository financeRepository;
    private BankRepository bankRepository;

    public FinanceDataManufacture(ModelMapper modelMapper, FinanceRepository financeRepository, BankRepository bankRepository) {
        this.modelMapper = modelMapper;
        this.financeRepository = financeRepository;
        this.bankRepository = bankRepository;
    }

    public void readData() throws IOException {

        ClassPathResource resource = new ClassPathResource("data/finance-supplied-data.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);

        ArrayList<Bank> bankCodeName = new ArrayList<>();
        ArrayList<FinanceDto> financeData = new ArrayList<>();

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow rows = sheet.getRow(i);

            for (int k = 2; k < rows.getPhysicalNumberOfCells(); k++) {
                XSSFCell cells = rows.getCell(k);

                if (i == 1) {
                    String cellName = String.valueOf(rows.getCell(k));

                    if (StringUtils.hasText(cellName)) {

                        BankCode bankCode = BankCode.getBankCode(BankCode.regExrName(cellName));

                        BankDto bankDto = new BankDto();
                        bankDto.setInstituteCode(String.valueOf(bankCode));
                        bankDto.setInstituteName(BankCode.regExrName(cellName));

                        Bank bank = modelMapper.map(bankDto, Bank.class);
                        bankCodeName.add(bank);
                        this.bankRepository.save(bank);
                    }
                } else {
                    int bIndex = k - 2;

                    if (cells.getRawValue() != null) {

                        FinanceDto financeDto = new FinanceDto();
                        financeDto.setYear((int) Math.floor(Double.parseDouble(String.valueOf(rows.getCell(0)))));
                        financeDto.setMonth((int) Math.floor(Double.parseDouble(String.valueOf(rows.getCell(1)))));
                        financeDto.setInstituteCode(bankCodeName.get(bIndex));
                        financeDto.setAmount((int) Math.floor(Double.parseDouble(cells.getRawValue())));

                        financeData.add(financeDto);
                    }
                }
            }
        }

        financeData.forEach(financeDto -> {
            Finance finance = modelMapper.map(financeDto, Finance.class);
            this.financeRepository.save(finance);
        });
    }
}
