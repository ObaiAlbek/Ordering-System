package com.ordering.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ordering.model.*;

public interface KochRepository extends JpaRepository<Kunde, Long> {}
