package com.example.crud.Repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import com.example.crud.Entities.Formation;

public interface FormationRepository extends CrudRepository<Formation, Long> {
List<Formation> findByTitre(String titre);
List<Formation> findByTitreAndFormateur(String titre,String formateur);
}
