/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presetreaderandwriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author samum
 */
public class PresetReaderAndWriter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Presets presetsread = new Presets();
        Preset p1 = new Preset("Preset1", "grassa", 30, 10, 45);
        presetsread.add(p1);
        Preset p2 = new Preset("Preset2", "magra", 25, 3, 32);
        presetsread.add(p2);
        Preset p3 = new Preset("Preset3", "grassa", 30, 10, 45);
        presetsread.add(p3);
        System.out.println(presetsread.get(2).toString());
        System.out.println(presetsread.toString());
        ReaderWriter rw = new ReaderWriter("preset.txt");
        
        rw.WriteToFile(presetsread);

        presetsread = rw.ReadFromFile("preset.txt");

        Preset p = presetsread.get(1);        
        p.setTipo("cicciotta");
        p.setUmax(25);
        p.setNome("come va");
        
        rw.WriteToFile(presetsread);

    }

}
