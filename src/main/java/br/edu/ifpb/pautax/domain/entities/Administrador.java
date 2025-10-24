package br.edu.ifpb.pautax.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name ="administrador")
public class Administrador extends Usuario {

    public Administrador() {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        this.setRole(roles);
    }

}
