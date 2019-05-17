package jskim216.financeapi.financeInfo;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jskim216.financeapi.banks.Bank;
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

//    public List<FinanceItemDto> findAllGroupByYear() {
//
//        return queryFactory.select(Projections.bean(FinanceItemDto.class, finance.instituteCode, finance.year, finance.amount.sum().as("totalAmount")))
//                .from(finance)
//                .groupBy(finance.year)
//                .fetch();
//    }

    public List<Integer> findAllGroupByYear() {
        return queryFactory.select(finance.year)
                .from(finance)
                .groupBy(finance.year)
                .fetch();
    }

    public List<Bank> findAllGroupByBank() {
        return queryFactory.select(finance.instituteCode)
                .from(finance)
                .groupBy(finance.instituteCode)
                .fetch();
    }

    public List<FinanceItemDto> findAllWhereYearGroupByBank(int year) {
        return queryFactory.select(Projections.bean(FinanceItemDto.class, finance.instituteCode.instituteName, finance.amount.sum().as("totalAmount")))
                .from(finance)
                .where(finance.year.eq(year))
                .groupBy(finance.instituteCode.id)
                .fetch();
    }

}
