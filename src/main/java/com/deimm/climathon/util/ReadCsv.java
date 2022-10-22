package com.deimm.climathon.util;

import com.deimm.climathon.dto.Emission;
import com.deimm.climathon.dto.Humidity;
import com.deimm.climathon.dto.Raindrops;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReadCsv {

    private static final String COMMA_DELIMITER = ";";
    private List<Object> emissions;
    private List<Object> humidity;
    private List<Object> raindrops;
    private List<Object> sunlight;
    private List<Object> temperature;
    private List<Object> trees;
    private List<Object> waterQuality;

    public ReadCsv(){
        this.emissions = new ArrayList<>();
        this.humidity = new ArrayList<>();
        this.raindrops = new ArrayList<>();
        this.sunlight = new ArrayList<>();
        this.temperature = new ArrayList<>();
        this.trees = new ArrayList<>();
        this.waterQuality = new ArrayList<>();
    }

    public void fillLists(){
        readCsv("emissions.csv", emissions);
        readCsv("humidity.csv", humidity);
        readCsv("raindrops.csv", raindrops);
        readCsv("sunlight.csv", sunlight);
        readCsv("temperatureAvg.csv", temperature);
        readCsv("trees.csv", trees);
        readCsv("waterQuality.csv", waterQuality);
    }

    private void readCsv(String filename, List<Object> list){
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/data/" + filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                switch (filename) {
                    case "emissions.csv" -> list.add(toEmission(values));
                    case "humidity.csv" -> list.add(toHumidity(values));
                    case "raindrops.csv" -> list.add(toRaindrops(values));

                    default -> System.out.println("Nepodporovany typ do listu " + filename);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Raindrops toRaindrops(String[] values) {
        Raindrops r = new Raindrops();
        Optional.ofNullable(values[0]).ifPresent(r::setRegion);
        Optional.ofNullable(values[1]).ifPresent(r::setCityPart);
        Optional.ofNullable(values[2]).ifPresent(r::setYear);
        if (values.length > 3) {
            Optional.ofNullable(values[3]).ifPresent(r::setRain);
        }
        return r;
    }

    private Humidity toHumidity(String[] values) {
        Humidity h = new Humidity();
        Optional.ofNullable(values[0]).ifPresent(h::setRegion);
        Optional.ofNullable(values[1]).ifPresent(h::setCityPart);
        Optional.ofNullable(values[2]).ifPresent(h::setYear);
        if (values.length > 3) {
            Optional.ofNullable(values[3]).ifPresent(h::setRelativeHumidity);
        }
        return h;
    }

    private Emission toEmission(String[] values) {
        Emission e = new Emission();
        Optional.ofNullable(values[0]).ifPresent(e::setYear);
        Optional.ofNullable(values[1]).ifPresent(e::setMonitoringStation);
        Optional.ofNullable(values[2]).ifPresent(e::setSubstance);
        if (values.length > 3) {
            Optional.ofNullable(values[3]).ifPresent(e::setValue);
        }
        return e;
    }

    public List<Object> getEmissions() {
        return emissions;
    }

    public void setEmissions(List<Object> emissions) {
        this.emissions = emissions;
    }

    public List<Object> getHumidity() {
        return humidity;
    }

    public void setHumidity(List<Object> humidity) {
        this.humidity = humidity;
    }

    public List<Object> getRaindrops() {
        return raindrops;
    }

    public void setRaindrops(List<Object> raindrops) {
        this.raindrops = raindrops;
    }

    public List<Object> getSunlight() {
        return sunlight;
    }

    public void setSunlight(List<Object> sunlight) {
        this.sunlight = sunlight;
    }

    public List<Object> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<Object> temperature) {
        this.temperature = temperature;
    }

    public List<Object> getTrees() {
        return trees;
    }

    public void setTrees(List<Object> trees) {
        this.trees = trees;
    }

    public List<Object> getWaterQuality() {
        return waterQuality;
    }

    public void setWaterQuality(List<Object> waterQuality) {
        this.waterQuality = waterQuality;
    }
}
