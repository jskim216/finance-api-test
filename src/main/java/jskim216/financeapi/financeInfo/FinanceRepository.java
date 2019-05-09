package jskim216.financeapi.financeInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FinanceRepository extends JpaRepository<Finance, Integer> {

    @Query(value = "SELECT SUM(amount), year, institute_code FROM finance GROUP BY year, institute_code", nativeQuery = true)
    Page<Finance> findAllGroupByYearly(Pageable pageable);
}
