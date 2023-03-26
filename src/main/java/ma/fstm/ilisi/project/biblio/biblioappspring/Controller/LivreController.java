package ma.fstm.ilisi.project.biblio.biblioappspring.Controller;

import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Exemplaire;
import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Livre;
import ma.fstm.ilisi.project.biblio.biblioappspring.service.ExemplaireService;
import ma.fstm.ilisi.project.biblio.biblioappspring.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class LivreController {
@Autowired
private LivreService livreService;
@Autowired
private ExemplaireService exemplaireService;
@RequestMapping("/")
    public String booksView(Model model){
    List<Livre> livres = livreService.retrieveAllLivres();
    model.addAttribute("livres",livres);
    return "BooksList";
}
@RequestMapping("/newbook")
public String showNewBookPage(Model model)
{
    Livre livre = new Livre();
    model.addAttribute("livre", livre);
    return "newbook";
}

    @RequestMapping(value = "/savebook", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("livre") Livre livre,@RequestParam("nbrexemplaire") int nbrexemplaire) {
        livreService.createLivre(livre);
        for(int i=0;i<nbrexemplaire;i++){
            Exemplaire exemplaire=new Exemplaire(exemplaireService.countExemplaire()+i);
            exemplaire.setLivre(livre);
            exemplaireService.createExemplaire(exemplaire);
        }
        return "redirect:/";
    }
    @RequestMapping("/delete/{isbn}")
    public String deleteProduct(@PathVariable(name = "isbn") String isbn) {
        livreService.deleteLivre(isbn);
        return "redirect:/";
    }

    @RequestMapping("/edit/{isbn}")
    public String showEditProductPage(@PathVariable(name = "isbn") String isbn, Model model) {
        Livre livre = livreService.retrieveLivre(isbn);
        model.addAttribute("livre", livre);
        return "editbook";
    }

    @RequestMapping("/update")
    public String updateProduct(@ModelAttribute("livre") Livre livre) {
        livreService.updateLivre(livre);
        return "redirect:/";
    }
    @RequestMapping("/retreave")
    public String retrieveProduct(@RequestParam("isbn") String isbn, Model model) {
        List<Livre> livres=new ArrayList<>();

        if(livreService.isLivreExist(isbn)){
        Livre livre = livreService.retrieveLivre(isbn);
        livres.add(livre);
    }
        else
         livres = livreService.retrieveAllLivres();
        model.addAttribute("livres",livres);
        return "BooksList";

    }
}
