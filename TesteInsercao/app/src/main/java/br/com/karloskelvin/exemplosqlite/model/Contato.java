package br.com.karloskelvin.exemplosqlite.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Karlos Kelvin on 17/12/15.
 * Desenvolvedor de Sistemas - UFCA
 * Analista e Desenvolvedor de Sistemas.
 */
public class Contato extends RealmObject {

    @PrimaryKey
    private long id;
    private String nome;
    private String telefone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

