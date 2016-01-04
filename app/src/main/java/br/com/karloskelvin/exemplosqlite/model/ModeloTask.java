package br.com.karloskelvin.exemplosqlite.model;

/**
 * Created by Karlos Kelvin on 18/12/15.
 * Desenvolvedor de Sistemas - UFCA
 * Analista e Desenvolvedor de Sistemas.
 */
public class ModeloTask {

    public String bd;
    public long qtdRegistro;

    public ModeloTask(long qtdRegistro, String bd) {
        this.qtdRegistro = qtdRegistro;
        this.bd = bd;
    }
}
