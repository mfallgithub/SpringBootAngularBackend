package com.nadhem.produits.restcontrollers;

import com.nadhem.produits.dto.ProduitDTO;
import com.nadhem.produits.entities.Produit;
import com.nadhem.produits.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * controller class
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProduitRestController {

    @Autowired
    ProduitService produitService;
    @GetMapping("/all")
    public List<ProduitDTO> getAllProduits() {
        return produitService.getAllProduits();
    }
    @GetMapping(value = "/getById/{id}")
    public ProduitDTO getProduitById(@PathVariable("id") Long id){
        return produitService.getProduit(id);
    }

    @PostMapping("/addProd")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProduitDTO createProduit(@RequestBody ProduitDTO produitDTO){
        return produitService.saveProduit(produitDTO);
    }

    @PutMapping("/updateProd")
    public ProduitDTO updateProduit(@RequestBody ProduitDTO produitDTO){
        return produitService.updateProduit(produitDTO);
    }

    @DeleteMapping(value = "/delProd/{id}")
    public void deleteProduit(@PathVariable("id") Long id){
        produitService.deleteProduitById(id);
    }

    @GetMapping(value = "/prodscat/{idCat}")
    public List<Produit> getProduitsByCatId(@PathVariable("idCat") Long idCat){
        return produitService.findByCategorieIdCat(idCat);
    }

    @GetMapping("/prodsByName/{nom}")
    public List<Produit>findByNomProduitContains(@PathVariable("nom") String nom){
        return produitService.findByNomProduitContains(nom);
    }
}
