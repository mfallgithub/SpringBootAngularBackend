package com.nadhem.produits.restcontrollers;


import com.nadhem.produits.entities.Categorie;
import com.nadhem.produits.repositories.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cat")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategorieRESTController {

    private final CategorieRepository categorieRepository;
    @GetMapping
    public List<Categorie> getAllCategories(){
        return categorieRepository.findAll();
    }
    @GetMapping(value = "/{id}")
    public Categorie getCategorieById(@PathVariable("id") Long id){
        return categorieRepository.findById(id).get();
    }

}
