package com.engeto.flowers;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlantList {
    public static final String DELIMITER = "\t";

    private ArrayList<Plant> list = new ArrayList<>();

    public void addPlant(Plant plants){ list.add(plants);}

    public Plant getPlant(int index){return list.get(index);}

    public void removePlant(int index) {list.remove(index);}

    public int getSize() {return list.size();}

    public void importFromTextFile(String fileName) throws PlantException{
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))){
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                try {
                    this.addPlant(Plant.parse(inputLine, DELIMITER));
                } catch (ParseException e) {
                    throw new PlantException("Invadil input file: "+fileName);
                }

            }
        }catch (FileNotFoundException ex) {
            throw new PlantException("Soubor " + fileName + " nenalezen");
        }
    }

    public void exportToTextFile(String fileName) throws PlantException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Plant plants: list) {
                String printPlant = plants.prepareOutput(DELIMITER);
                writer.write(printPlant);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
