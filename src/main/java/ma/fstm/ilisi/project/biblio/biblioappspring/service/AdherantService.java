package ma.fstm.ilisi.project.biblio.biblioappspring.service;

import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Adherant;
import ma.fstm.ilisi.project.biblio.biblioappspring.dao.AdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AdherantService {
 @Autowired
    private AdherantRepository adherantRepository;
 public void createAdherant(Adherant adherant){
        adherantRepository.save(adherant);
    }

    public void updateAdherant(Adherant adherant){
        adherantRepository.save(adherant);
    }
    public void deleteAdherant(String cin){
        adherantRepository.deleteById(cin);
    }
    public Adherant retrieveAdherant(String cin){
        return adherantRepository.findById(cin).get();
    }
    public List<Adherant> retrieveAllAdherants(){
        return (List<Adherant>) adherantRepository.findAll();
    }
    public boolean isAdherantExist(String cin){
        return adherantRepository.existsById(cin);
    }

}
