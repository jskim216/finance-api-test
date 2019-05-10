package jskim216.financeapi.financeInfo;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jskim216.financeapi.financeInfo.QFinance.finance;

@Repository
public class FinanceRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public FinanceRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Finance.class);
        this.queryFactory = jpaQueryFactory;
    }

    public List<FinanceDto> findAllGroupByYear() {

        return queryFactory.select(Projections.constructor(FinanceDto.class, finance.instituteCode, finance.year, finance.amount.sum().as("total_amount")))
                .from(finance)
                .groupBy(finance.instituteCode, finance.year)
                .fetch();
    }
}
