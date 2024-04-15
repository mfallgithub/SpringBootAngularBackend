package com.nadhem.produits;

import com.nadhem.produits.entities.Categorie;
import com.nadhem.produits.entities.Produit;
import com.nadhem.produits.repositories.ProduitRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class ProduitsApplicationTests {

    @Autowired
    private ProduitRepository produitRepository;

    @Test
    public void testCreateProduit() {
//        Produit produit = new Produit("PC Dell", 2200.500, new Date());
        Produit produit = new Produit("toto", 1000.0, new Date(), null);
        produitRepository.save(produit);
    }

    @Test
    public void testFindProduit() {
        Produit p = produitRepository.findById(1L).get();
        System.out.println(p);
    }

    @Test
    public void testUpdateProduit() {
        Produit p = produitRepository.findById(1L).get();
        p.setPrixProduit(2000.0);
        produitRepository.save(p);
        System.out.println(p);
    }

    @Test
    public void testDeleteProduit() {
        produitRepository.deleteById(1L);
    }


    @Test
    public void testFindAllProducts() {
        List<Produit> prods = produitRepository.findAll();
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    @Test
    public void testFindProduitByNom() {
        List<Produit> prods = produitRepository.findByNomProduit("PS 4");
        for (Produit prod : prods) {
            System.out.println(prod);
        }

    }

    @Test
    public void testFindProduitByNomContains() {
        List<Produit> prods = produitRepository.findByNomProduitContains("P");
        for (Produit prod : prods) {
            System.out.println(prod);
        }
    }

    @Test
    public void testFindByNomPrix() {
        List<Produit> prods = produitRepository.findByNomPrix("PS 4", 1000.0);
        for (Produit p : prods) {
            System.out.println(p);
        }
    }
    @Test
    public void testFindByCategorie() {
        Categorie cat= new Categorie();
         cat.setIdCat(1L);
         List<Produit> prods= produitRepository.findByCategorie(cat);
         for(Produit p: prods){
             System.out.println(p);
         }
    }

    @Test
    public void testFindByCategorieIdCat() {
        List<Produit> prods= produitRepository.findByCategorieIdCat(1L);
        for(Produit p: prods){
            System.out.println(p);
        }
    }
    @Test
    public void testfindByOrderByNomProduitAsc() {
        List<Produit> prods= produitRepository.findByOrderByNomProduitAsc();
        for(Produit p: prods){
            System.out.println(p);
        }
    }

    @Test
    public void testTrierProduitsNomsPrix() {
        List<Produit> prods= produitRepository.trierProduitsNomsPrix();
        for(Produit p: prods){
            System.out.println(p);
        }
    }

}
