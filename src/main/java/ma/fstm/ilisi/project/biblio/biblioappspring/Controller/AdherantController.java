package ma.fstm.ilisi.project.biblio.biblioappspring.Controller;

import ma.fstm.ilisi.project.biblio.biblioappspring.bo.Adherant;
import ma.fstm.ilisi.project.biblio.biblioappspring.service.AdherantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdherantController {
    @Autowired
    private AdherantService adherantService;
    @RequestMapping("/AdherantList")
    public String AdherantsView(Model model){
        List<Adherant> adherants = adherantService.retrieveAllAdherants();
        model.addAttribute("adherants",adherants);
        return "AdherantList";
    }
    @RequestMapping("/newadherant")
    public String showNewAdherantPage(Model model)
    {
        Adherant adherant = new Adherant();
        model.addAttribute("adherant", adherant);
        return "newadherant";
    }

    @RequestMapping(value = "/saveadherant", method = RequestMethod.POST)
    public String saveAdherant(@ModelAttribute("adherant") Adherant adherant) {
        adherantService.createAdherant(adherant);
        return "redirect:/AdherantList";
    }
    @RequestMapping("/deleteadherant/{id}")
    public String deleteAdherant(@PathVariable(name = "id") String id) {
        adherantService.deleteAdherant(id);
        return "redirect:/AdherantList";
    }

    @RequestMapping("/editadherant/{id}")
    public String showEditAdherantPage(@PathVariable(name = "id") String id, Model model) {
        Adherant adherant = adherantService.retrieveAdherant(id);
        model.addAttribute("adherant", adherant);
        return "editadherant";
    }

    @RequestMapping("/updateadherant")
    public String updateAdherant(@ModelAttribute("adherant") Adherant adherant) {
        adherantService.updateAdherant(adherant);
        return "redirect:/AdherantList";
    }
    @RequestMapping("/retreaveadherant")
    public String retrieveAdherant(@RequestParam("id") String id, Model model) {
        List<Adherant> adherants=new ArrayList<>();

        if(adherantService.isAdherantExist(id)){
            Adherant adherant = adherantService.retrieveAdherant(id);
            adherants.add(adherant);
        }
        model.addAttribute("adherants",adherants);
        return "AdherantList";
    }



}
