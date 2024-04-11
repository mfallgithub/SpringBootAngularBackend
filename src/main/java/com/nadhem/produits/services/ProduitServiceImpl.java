package com.nadhem.produits.services;

import com.nadhem.produits.dto.ProduitDTO;
import com.nadhem.produits.entities.Categorie;
import com.nadhem.produits.entities.Produit;
import com.nadhem.produits.repositories.ProduitRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public ProduitDTO saveProduit(ProduitDTO p) {
        return convertEntityToDto(produitRepository.save(convertDtoToEntity(p)));
    }

    @Override
    public ProduitDTO updateProduit(ProduitDTO p) {
        return convertEntityToDto(produitRepository.save(convertDtoToEntity(p)));
    }

    @Override
    public void deleteProduit(Produit p) {
        produitRepository.delete(p);
    }

    @Override
    public void deleteProduitById(Long id) {
        produitRepository.deleteById(id);
    }

    @Override
    public ProduitDTO getProduit(Long id) {
        return convertEntityToDto(produitRepository.findById(id).get());
    }

    @Override
    public List<ProduitDTO> getAllProduits() {
        return produitRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Produit> findByNomProduit(String nom) {
        return produitRepository.findByNomProduit(nom);
    }

    @Override
    public List<Produit> findByNomProduitContains(String nom) {
        return produitRepository.findByNomProduitContains(nom);
    }

    @Override
    public List<Produit> findByNomPrix(String nom, Double prix) {
        return produitRepository.findByNomPrix(nom, prix);
    }

    @Override
    public List<Produit> findByCategorie(Categorie categorie) {
        return produitRepository.findByCategorie(categorie);
    }

    @Override
    public List<Produit> findByCategorieIdCat(Long id) {
        return produitRepository.findByCategorieIdCat(id);
    }

    @Override
    public List<Produit> findByOrderByNomProduitAsc() {
        return produitRepository.findByOrderByNomProduitAsc();
    }

    @Override
    public List<Produit> trierProduitsNomsPrix() {
        return produitRepository.trierProduitsNomsPrix();
    }

    @Override
    public ProduitDTO convertEntityToDto(Produit produit) {
        //ancienne version
       /* return ProduitDTO.builder()
                .idProduit(p.getIdProduit())
                .nomProduit(p.getNomProduit())
                .dateCreation(p.getDateCreation())
                .categorie(p.getCategorie())
                .build();*/

        //version avec ModelMapper
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(produit, ProduitDTO.class);
    }

    @Override
    public Produit convertDtoToEntity(ProduitDTO produitDTO) {
        //l'ancienne version
        /*Produit produit = new Produit();
        produit.setIdProduit(produitDTO.getIdProduit());
        produit.setNomProduit(produitDTO.getNomProduit());
        produit.setPrixProduit(produitDTO.getPrixProduit());
        produit.setDateCreation(produitDTO.getDateCreation());
        produit.setCategorie(produitDTO.getCategorie());*/
        //nouvelle version
        return modelMapper.map(produitDTO, Produit.class);
    }
}
