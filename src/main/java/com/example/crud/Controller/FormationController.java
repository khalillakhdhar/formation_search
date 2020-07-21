package com.example.crud.Controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.crud.Repositories.FormationRepository;
import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.Entities.Formation;
@RestController
@RequestMapping("formation")
public class FormationController {
@Autowired
FormationRepository formationRepository;
@GetMapping()
List<Formation> getFormations()
{
return (List<Formation>) formationRepository.findAll();
}

@PostMapping()
public Formation createFormation(@Valid @RequestBody Formation formation)
{
return formationRepository.save(formation);	

}
@GetMapping("/{id}")
public Formation getFormationById(@PathVariable(value = "id") long idFormation)
{
	return formationRepository.findById(idFormation).orElseThrow(() -> new
			ResourceNotFoundException("Formation", "id", idFormation));
}
@GetMapping("/titre/{titre}")
public List<Formation> getFormationByTitre(@PathVariable(value = "titre") String titre)
{
	return (List<Formation>) formationRepository.findByTitre(titre);


}
@GetMapping("/filter/{titre}/{formateur}")
public List<Formation> getFormationBymultiple(@PathVariable(value = "titre") String titre,@PathVariable(value = "formateur") String formateur)
{
	return (List<Formation>) formationRepository.findByTitreAndFormateur(titre, formateur);

}
@DeleteMapping("/{id}")
public ResponseEntity<?> deleteFormation(@PathVariable(value = "id") long idFormation)
{
Formation formation=formationRepository.findById(idFormation).orElseThrow(() -> new
		ResourceNotFoundException("Formation", "id", idFormation));	
formationRepository.delete(formation);
return ResponseEntity.ok().build();
}

}
