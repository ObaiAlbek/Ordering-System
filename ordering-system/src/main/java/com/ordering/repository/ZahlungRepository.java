package com.ordering.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ordering.model.*;

public interface ZahlungRepository extends JpaRepository<Zahlung, Long> {}
