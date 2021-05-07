package com.modelagem.software.jogo.mario.world.jogo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrador implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idAdministrador;
    @Column(unique = true)
    private String username;

    private String password;
    


    public Integer getIdAdministrador() {
    	return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
    	this.idAdministrador = idAdministrador;
    }

    public String getUsername() {
    	return username;
    }

    public void setUsername(String username) {
    	this.username = username;
    }

    public String getPassword() {
    	return password;
    }

    public void setPassword(String password) {
    	this.password = password;
    }

}
