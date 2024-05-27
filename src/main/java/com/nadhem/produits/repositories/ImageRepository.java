package com.nadhem.produits.repositories;

import com.nadhem.produits.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
