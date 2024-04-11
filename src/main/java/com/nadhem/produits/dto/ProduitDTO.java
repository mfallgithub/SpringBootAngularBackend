package com.nadhem.produits.dto;

import com.nadhem.produits.entities.Categorie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduitDTO {

    private Long idProduit;
    private String nomProduit;
    private Date dateCreation;
    private  String nomCat;
}
