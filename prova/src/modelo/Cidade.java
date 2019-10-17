/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author Administrador
 */
public class Cidade {
    
    private Integer codigo;
    private String nome;
    private String sigla;
    private Integer hab;
    private LocalDate datae; 
    private Double area;
    private Integer dist;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getHab() {
        return hab;
    }

    public void setHab(Integer hab) {
        this.hab = hab;
    }

    public LocalDate getDatae() {
        return datae;
    }

    public void setDatae(LocalDate datae) {
        this.datae = datae;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(Integer dist) {
        this.dist = dist;
    }

    @Override
    public String toString() {
        return  nome;
    }

   
    
    
}
