package com.myproject.commandes.dao;
import com.myproject.commandes.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandDao extends JpaRepository<Commande, Integer> {

}
