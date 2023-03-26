package ma.fstm.ilisi.project.biblio.biblioappspring.dao;

import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Emprunt;
import ma.fstm.ilisi.project.biblio.biblioappspring.bo.EmpruntId;
import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Exemplaire;
import org.springframework.data.repository.CrudRepository;

public interface EmpruntRepository extends CrudRepository<Emprunt, EmpruntId> {

}
