/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettotecnologie;

import java.util.ArrayList;

/**
 *
 *@author User
 */
public class Presets extends ArrayList<Preset> {

    public Presets() {

    }

    public String toCSV() {
        String s = "";
        for (Preset p : this) {
            s += p.toCSV() + "\n";
        }
        return s;
    }

    public String toString() {
        String s = "";
        for (Preset p : this) {
            s += p.toString() + "\n";
        }
        return s;
    }
    
    public Preset find(String pres){
        Preset pre = null;
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getNome() == pres){
                pre = this.get(i);
            }
        }
        return pre;
    }

}
