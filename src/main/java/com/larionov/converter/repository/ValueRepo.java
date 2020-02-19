package com.larionov.converter.repository;

import com.larionov.converter.entities.Quote;
import com.larionov.converter.entities.Currency;
import com.larionov.converter.entities.QuoteKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Repository
public interface ValueRepo extends JpaRepository<Quote, QuoteKey> {
    public List<Quote> findByQuoteKey(QuoteKey quoteKey);
}
