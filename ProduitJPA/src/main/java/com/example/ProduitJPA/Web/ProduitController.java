package com.example.ProduitJPA.Web;

import com.example.ProduitJPA.Dao.ProduitRepository;
import com.example.ProduitJPA.entities.Produit;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProduitController {


    /*
    Comments for git tests
     */
    @Autowired
    ProduitRepository produitRepository;

    @RequestMapping(value = "/")
    public String home()
    {
        return "redirect:/user/index";
    }

    @RequestMapping(value = "403")
    public String accessDenied()
    {
        return "403";
    }

    @RequestMapping(value = "/user/index")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
                        @RequestParam(name = "size", defaultValue = "5")int s, @RequestParam(name = "motCle",defaultValue = "") String mc)
    {
        Page<Produit> pageProduits=produitRepository.Chercher("%"+mc+"%",PageRequest.of(p, s));
        model.addAttribute("listProduits",pageProduits.getContent());
        int[] pages=new int[pageProduits.getTotalPages()];
         model.addAttribute("pages",pages);
         model.addAttribute("size",s);
         model.addAttribute("pageCourante",p);
         model.addAttribute("motCle",mc);
        return "produits";
    }

    @RequestMapping(value = "/admin/delete",method = RequestMethod.GET)
    public String delete(Long id,int page,int size,String motCle)
    {
        produitRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&size="+size+"&motCle="+motCle;
    }

    @RequestMapping(value = "/admin/form",method = RequestMethod.GET)
    public String formProduit(Model model){
        model.addAttribute("produit",new Produit());
        return "FormProduit";
    }

    @RequestMapping(value = "/admin/save",method = RequestMethod.POST)
    public String save(Model model, @Valid Produit produit, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "FormProduit";  //on revient au form
        produitRepository.save(produit);
        return "Confirmation";
    }

    @RequestMapping(value = "/admin/edit",method = RequestMethod.GET)
    public String Edit(Model model,Long id){

        Produit produit=produitRepository.getById(id);
        model.addAttribute("produit",produit);
        return "EditProduit";
    }

    @RequestMapping(value = "/login")
    public String login()
    {
        return "login";
    }
}
