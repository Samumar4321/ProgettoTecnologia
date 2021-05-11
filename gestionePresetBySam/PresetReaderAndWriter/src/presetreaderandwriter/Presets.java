/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presetreaderandwriter;

import java.util.ArrayList;

/**
 *
 * @author samum
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

}
