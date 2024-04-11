package com.nadhem.produits.restcontrollers;

import com.nadhem.produits.dto.ProduitDTO;
import com.nadhem.produits.entities.Produit;
import com.nadhem.produits.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping
    public List<ProduitDTO> getAllProduits() {
        return produitService.getAllProduits();
    }
    @GetMapping(value = "/{id}")
    public ProduitDTO getProduitById(@PathVariable("id") Long id){
        return produitService.getProduit(id);
    }
    @PostMapping
    public ProduitDTO createProduit(@RequestBody Produit produit){
        return produitService.saveProduit(produit);
    }
    @PutMapping
    public Produit updateProduit(@RequestBody Produit produit){
        return produitService.updateProduit(produit);
    }
    @DeleteMapping(value = "/{id}")
    public void deleteProduit(@PathVariable("id") Long id){
        produitService.deleteProduitById(id);
    }

    @GetMapping(value = "/prodscat/{idCat}")
    public List<Produit> getProduitsByCatId(@PathVariable("idCat") Long idCat){
        return produitService.findByCategorieIdCat(idCat);
    }
}
