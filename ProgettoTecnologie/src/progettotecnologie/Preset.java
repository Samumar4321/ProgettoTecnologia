/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettotecnologie;

/**
 *
 *@author User
 */
public class Preset {

    private String nome;
    private String tipo;
    private int Umax;
    private int Umin;
    private int luceIdeale;
    private int tempMax;
    private int tempMin;

    public int getTempMax() {
        return tempMax;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    

    public Preset() {
        this.nome = "";
        this.tipo = "";
        this.Umax = 0;
        this.Umin = 0;
        this.luceIdeale = 0;
        this.tempMax=0;
        this.tempMin=0;
    }

    public Preset(String nome, String tipo, int Umax, int Umin, int luceIdeale,int tempMax,int tempMin) {
        this.nome = nome;
        this.tipo = tipo;
        this.Umax = Umax;
        this.Umin = Umin;
        this.luceIdeale = luceIdeale;
        this.tempMax=tempMax;
        this.tempMin=tempMin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getUmax() {
        return Umax;
    }

    public void setUmax(int Umax) {
        this.Umax = Umax;
    }

    public int getUmin() {
        return Umin;
    }

    public void setUmin(int Umin) {
        this.Umin = Umin;
    }

    public int getLuceIdeale() {
        return luceIdeale;
    }

    public void setLuceIdeale(int luceIdeale) {
        this.luceIdeale = luceIdeale;
    }

    public String toString() {
        String s = "";
        s = nome + " - " + tipo + " - " + Umax + " - " + Umin + " - " + luceIdeale+ " - " +tempMin+ " - " +tempMax;
        return s;
    }

    public String toCSV() {
        String s = "";
        s = nome + ";" + tipo + ";" + Umax + ";" + Umin + ";" + luceIdeale+ ";" +tempMin+ ";" +tempMax;
        return s;
    }

   

}
