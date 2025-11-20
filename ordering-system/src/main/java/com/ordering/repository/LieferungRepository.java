package com.ordering.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ordering.model.*;

public interface LieferungRepository extends JpaRepository<Lieferung, Long> {}
