package com.picpaysimplificado.repositories;

import com.picpaysimplificado.domain.transation.Transation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrasationRepository extends JpaRepository<Transation,Long> {
}
