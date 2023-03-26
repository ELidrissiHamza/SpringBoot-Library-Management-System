package ma.fstm.ilisi.project.biblio.biblioappspring.dao;

import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Adherant;
import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Exemplaire;
import org.springframework.data.repository.CrudRepository;

public interface ExemplaireRepository extends CrudRepository<Exemplaire, Integer> {

}
