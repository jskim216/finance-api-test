package jskim216.financeapi.financeInfo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class FinanceResource extends Resource<Finance> {

    public FinanceResource(Finance content, Link... links) {
        super(content, links);
    }
}
