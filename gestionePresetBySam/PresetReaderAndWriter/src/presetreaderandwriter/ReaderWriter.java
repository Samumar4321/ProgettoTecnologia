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
public class ReaderWriter {

    String filename;

    public ReaderWriter(String path) {
        filename = path;
    }

    public void WriteToFile(Presets ps) {
        // Write the content in file 
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            String fileContent = "";
            for (int i = 0; i < ps.size(); i++) {
                fileContent += ps.get(i).toCSV() + "\n";
            }
            bufferedWriter.write(fileContent);
        } catch (IOException e) {
            // Exception handling
            System.out.println(e);
        }
    }

    public Presets ReadFromFile(String _filename) {
        // Write the content in file 
        Presets ps = new Presets();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(_filename))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] temp = line.split(";");
                Preset p = new Preset(temp[0], temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
                ps.add(p);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please specify the right file...");
        } catch (IOException e) {
            System.out.println(e);
        }
        return ps;
    }

    public Presets ReadFromFile() {
        // Write the content in file 
        Presets ps = new Presets();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] temp = line.split(";");
                Preset p = new Preset(temp[0], temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
                ps.add(p);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please specify the right file...");
        } catch (IOException e) {
            System.out.println(e);
        }
        return ps;
    }

}
