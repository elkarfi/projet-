package com.myproject.commandes.controller;

import com.myproject.commandes.configurations.ApplicationPropertiesConfiguration;
import com.myproject.commandes.dao.CommandDao;
import com.myproject.commandes.model.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CommandeController implements HealthIndicator {
    @Autowired
    CommandDao commandDao;
    @Autowired
    ApplicationPropertiesConfiguration appProperties;

    // Affiche la liste de tous les produits disponibles
    @GetMapping(value = "/Commandes")
    public List<Commande> listeDesCommandes() {
        System.out.println(" ********* CommandeController listeDesCommandes() ");
        List<Commande> commandes = commandDao.findAll();
        if (commandes.isEmpty())
            System.out.println("Aucun commande n'est disponible ");

        List<Commande> listeLimitee = commandes.subList(0, appProperties.getLimitDeCommandes());

        return listeLimitee ;
    }


//
//    @GetMapping("/Commandes")
//    public ResponseEntity<List<Commande>> getAllTutorials( ) {
//        try {
//            List<Commande> commandes = new ArrayList<Commande>();
//
//            commandDao.findAll().forEach(commandes::add);
//
//            if (commandes.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            List<Commande> listeLimitee = commandes.subList(0, appProperties.getLimitDeCommandes());
//
//            return new ResponseEntity<>(listeLimitee, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }



    @GetMapping("/Commandes/{id}")
    public ResponseEntity<Commande> getTutorialById(@PathVariable("id") int id) {

        Commande commande = commandDao.findById(id).orElse(null);

        if (commande != null) {
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }









    @PutMapping("/Commandes/{id}")
    public ResponseEntity<Commande> updateTutorial(@PathVariable("id") int id, @RequestBody Commande commande) {
        Commande  newCommande = commandDao.findById(id).orElse(null);

        if (newCommande != null) {

            newCommande.setId(commande.getId());
            newCommande.setDescription(commande.getDescription());
            newCommande.setQuantity(commande.getQuantity());
            newCommande.setDate(commande.getDate());
            newCommande.setAmount(commande.getAmount());

            return new ResponseEntity<>( commandDao.save(newCommande), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/Commandes")
    public ResponseEntity<Commande> createTutorial(@RequestBody Commande commande) {
        try {
            Commande _commande = commandDao
                    .save(new Commande(commande.getDescription(), commande.getDate(), commande.getQuantity(),commande.getAmount()));
            return new ResponseEntity<>(_commande, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }












    @DeleteMapping("/Commandes/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") int id) {
        try {
            commandDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/Commandes")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            commandDao.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }








    @Override
    public Health health() {
        System.out.println("****** Actuator : CommandeController health() ");
        List<Commande> products = commandDao.findAll();
        if (products.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }

}
