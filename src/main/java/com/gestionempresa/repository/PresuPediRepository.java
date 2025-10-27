package com.gestionempresa.repository;

import com.gestionempresa.model.PresuPedi;
import com.gestionempresa.model.PresuPediId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresuPediRepository extends JpaRepository<PresuPedi, PresuPediId> {
}

