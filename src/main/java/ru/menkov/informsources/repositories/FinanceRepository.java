package ru.menkov.informsources.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.menkov.informsources.model.commerce.Finance;

@Repository
public interface FinanceRepository extends CrudRepository<Finance,Integer> {
}
