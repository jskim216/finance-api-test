package jskim216.financeapi.financeInfo;

import com.querydsl.core.Tuple;
import jskim216.financeapi.banks.Bank;
import jskim216.financeapi.banks.BankRepository;
import jskim216.financeapi.data.FinanceDataManufacture;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

import static jskim216.financeapi.financeInfo.QFinance.finance;

@Controller
@RequestMapping(value = "/api/finance", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class FinanceApiController {

    private final ModelMapper modelMapper;
    private FinanceRepository financeRepository;
    private BankRepository bankRepository;

    @Autowired
    private FinanceRepositorySupport financeRepositorySupport;

    public FinanceApiController(FinanceRepository financeRepository, ModelMapper modelMapper, BankRepository bankRepository) {
        this.financeRepository = financeRepository;
        this.modelMapper = modelMapper;
        this.bankRepository = bankRepository;
    }

    @GetMapping("create-data")
    public void createData() throws IOException {
        FinanceDataManufacture financeDataManufacture = new FinanceDataManufacture(this.modelMapper, this.financeRepository, this.bankRepository);
        financeDataManufacture.readData();
    }

    @GetMapping("institution-list")
    public ResponseEntity institutionList(Pageable pageable) {
        Page<Bank> page = this.bankRepository.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("institution-list-yearly")
    public ResponseEntity institutionListYearly() {

//        Page<Finance> page = this.financeRepository.findAllGroupByYearly(pageable);
//        JPAQueryFactory queryFactory

        List<FinanceDto> result = this.financeRepositorySupport.findAllGroupByYear();

        return ResponseEntity.ok(result);
    }

}
