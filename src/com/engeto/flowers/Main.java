package com.engeto.flowers;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    private static final String INPUT_FILE = "kvetiny.txt";
    private static final String OUTPUT_FILE = "vystup.txt";

    public static void main(String[] args) {
        PlantList list = new PlantList();

        try {
            list.importFromTextFile(INPUT_FILE);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }

        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.getPlant(i).getwateringInfo());
        }
        list.addPlant(new Plant("Amarylis v obýváku"));
        list.addPlant(new Plant("Bazalka v kuchyni", 3, LocalDate.now()));
        list.removePlant(2);

        try {
            list.exportToTextFile(OUTPUT_FILE);
        } catch (PlantException e) {
            System.err.println(e.getMessage());
        }
    }
}
