package com.larionov.converter.repository;

import com.larionov.converter.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface CurrencyRepo extends JpaRepository<Currency, String> {

}
