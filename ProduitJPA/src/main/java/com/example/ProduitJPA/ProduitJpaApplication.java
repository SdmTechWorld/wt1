package com.example.ProduitJPA;

import com.example.ProduitJPA.Dao.ProduitRepository;
import com.example.ProduitJPA.entities.Produit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProduitJpaApplication {

	public static void main(String[] args) {
	 ApplicationContext ctx= SpringApplication.run(ProduitJpaApplication.class, args);

		ProduitRepository produitRepository=ctx.getBean(ProduitRepository.class);

		produitRepository.save(new Produit("Ordinateur",523,54));
		produitRepository.save(new Produit("Telephone",2653,54));
		produitRepository.save(new Produit("Moto",45,56));
		produitRepository.save(new Produit("Voiture",231,54));


		produitRepository.findAll().forEach(produit -> System.out.println(produit.getDesignation()));
	}

}
