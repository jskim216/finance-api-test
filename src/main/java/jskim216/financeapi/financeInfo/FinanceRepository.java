package jskim216.financeapi.financeInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface FinanceRepository extends JpaRepository<Finance, Integer>, QuerydslPredicateExecutor<Finance> {

}