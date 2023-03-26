package ma.fstm.ilisi.project.biblio.biblioappspring.Controller;

import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Adherant;
import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Emprunt;
import ma.fstm.ilisi.project.biblio.biblioappspring.bo.EmpruntId;
import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Livre;
import ma.fstm.ilisi.project.biblio.biblioappspring.service.AdherantService;
import ma.fstm.ilisi.project.biblio.biblioappspring.service.EmpruntService;
import ma.fstm.ilisi.project.biblio.biblioappspring.service.ExemplaireService;
import ma.fstm.ilisi.project.biblio.biblioappspring.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmpruntController {
    @Autowired
    private EmpruntService empruntService;
    @Autowired
    private LivreService livreService;
    @Autowired
    private AdherantService adherantService;
    @Autowired
    private ExemplaireService exemplaireService;
    @RequestMapping("/EmpruntList")
    public String EmpruntsView(Model model){
        //List<Emprunt> emprunts = empruntService.retrieveAllEmprunts();
        List<Emprunt> emprunts = empruntService.rettreaveEmpruntNonRendu();
        model.addAttribute("emprunts",emprunts);
        return "EmpruntList";
    }
    @GetMapping(value = "/deleteemprunt")
    public String deleteEmprunt(@RequestParam("cin") String cin,@RequestParam("idex") int idex,@RequestParam("date") String date, Model model) {
        System.out.println("cin ="+cin);
        EmpruntId idemp=new EmpruntId(idex,cin, date);
        Emprunt emprunt = new Emprunt(idemp,1);
        empruntService.updateEmprunt(emprunt);
        return "redirect:/EmpruntList";
    }

    @RequestMapping("/newemprunt")
    public String newEmprunt(Model model) {
        List<Livre> livres = livreService.retrieveAllLivres();
        model.addAttribute("livres", livres);
        List<Adherant> adherants = adherantService.retrieveAllAdherants();
        model.addAttribute("adherants", adherants);

        return "newemprunt";
    }
    @RequestMapping(value = "/saveemprunt", method = RequestMethod.POST)
    public String saveEmprunt(@RequestParam("cin") String cin,@RequestParam("isbn") String isbn, Model model) {
        System.out.println("cin ="+cin);
        int nbrexemplaire=exemplaireService.countExemplaireForLivre(isbn);
        int nbremprunt=empruntService.countEmpruntbyISBN(isbn);
        if(nbrexemplaire>nbremprunt&&nbrexemplaire>0){
            EmpruntId idemp=new EmpruntId(exemplaireService.getExemplaireByISBN(isbn).getIdexp(),cin, "2020-01-01");
            Emprunt emprunt = new Emprunt(idemp,0);
            empruntService.createEmprunt(emprunt);
        }
        else {
            model.addAttribute("error", "il n'y a pas d'exemplaire disponible");
            System.out.println("il n'y a pas d'exemplaire disponible");
        }
        return "redirect:/EmpruntList";
    }
}
