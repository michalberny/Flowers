package com.engeto.flowers;

import java.text.ParseException;
import java.time.LocalDate;

public class Plant {
    /*
     *   ATTRIBUTES
     */
    private String name;
    private String notes = "";
    private int frequencyOfWatering = 7;
    private LocalDate watering = LocalDate.now();
    private LocalDate planted = LocalDate.now();


    /*
    *   CONSTRUCTORS
    */
    public Plant(String name, String notes, int frequencyOfWatering, LocalDate watering,  LocalDate planted ) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public Plant(String name, int frequencyOfWatering, LocalDate planted ) {
        this.name = name;
        this.planted = planted;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public Plant(String name) {
        this.name = name;
    }

    /*
     *   METHODS
     */
    public String getwateringInfo() {
        String wateringInfo;
        wateringInfo = "Název květiny: "+name+" Datum poslední zálivky: "+watering+" Datum další doporučené zálivky: "+
                        watering.plusDays(frequencyOfWatering);
        return wateringInfo;
    }

    /*
     *   EXCEPTIONS
     */
    public void frequencyException (int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Záporná hodnota nebo 0");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public void wateringException (LocalDate watering) throws PlantException {
        if (watering.isBefore(planted)) {
            throw new PlantException("Datum poslední zálivky musí být po datu zasazení");
        }
        this.watering = watering;
    }

    public static Plant parse(String text, String delimiter) throws ParseException {
        String[] items = text.split(delimiter);

        int numberOfItems = items.length;
        if (numberOfItems != 5) throw new ParseException("Nesrávný počet položek na řádku", 0);

        String name = items[0];
        String notes = items[1];
        int frequencyOfWatering = Integer.parseInt(items[2]);
        LocalDate watering = LocalDate.parse(items[3]);
        LocalDate planted = LocalDate.parse(items[4]);

        return new Plant(name, notes, frequencyOfWatering, watering, planted);
    }

    public String prepareOutput(String delimiter) {
        return getName()+delimiter+getNotes()+delimiter+getFrequencyOfWatering()+delimiter
                +getWatering()+delimiter+getPlanted();
    }

    /*
     *   GETTERS AND SETTERS
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) {
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) {
        this.frequencyOfWatering = frequencyOfWatering;
    }
}
