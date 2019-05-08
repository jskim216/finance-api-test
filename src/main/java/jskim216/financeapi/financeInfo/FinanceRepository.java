package jskim216.financeapi.financeInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepository extends JpaRepository<Finance, Integer> {


//    Finance findAllByYearGroup

}
