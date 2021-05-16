package ru.menkov.informsources.repositories.commerce;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.menkov.informsources.model.commerce.Finance;

@Repository
public interface FinanceRepository extends CrudRepository<Finance,Integer> {
    Boolean existsFinanceByUserId(Integer userId);
    Boolean existsFinanceById(Integer id);
    Finance findFinanceById(Integer id);
    Finance findFinanceByUserId(Integer id);

    @Modifying
    @Transactional
    @Query(value = "update inf_sources.finance set card = :card where user_id= :userId", nativeQuery = true)
    void setFinanceCardByUserId(@Param("userId") Integer userId, @Param("card") String name);

    @Modifying
    @Transactional
    @Query(value = "update inf_sources.finance set agreement = :agreement where user_Id= :userId", nativeQuery = true)
    void setFinanceAgreementByUserId(@Param("userId") Integer userId, @Param("agreement") Boolean agreement);
}
