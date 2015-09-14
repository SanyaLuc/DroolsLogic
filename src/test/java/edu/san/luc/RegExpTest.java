package edu.san.luc;

import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sanya on 18.05.15.
 */
public class RegExpTest {

    @Test
    public void testReplaceAll() throws Exception {
        String input = "[a]-[a-gamma]+(a-alpha)*{a}-{b-gamma}+[a]";
        String result = input.replaceAll("-(?![\\[\\{\\(])","_");
        System.out.println(result);
    }

    @Test
    public void testAutoClosable() throws Exception {
        AutoCloseable resource = new AutoCloseable() {
            @Override
            public void close() throws Exception {

            }
        };

        try(AutoCloseable res = resource){

        }
    }

    @Test
    public void truckTest () {

        Truck pickupTruck1 = new Truck("Big 10", "Chevy", 1976);
        Truck pickupTruck2 = new Truck("Big 10", "Chevy", 1975);

        List<TruckPartsObject> parts = new ArrayList<TruckPartsObject>();
        TruckPartsObject truckPartsObject = new TruckPartsObject();
        truckPartsObject.setName("part1");
        parts.add(truckPartsObject);
        pickupTruck1.setParts(parts);

        Assert.assertThat(pickupTruck1, SamePropertyValuesAs.samePropertyValuesAs(pickupTruck2));
    }

    public class Truck {

        private String model;
        private String make;
        private int year;
        private List<TruckPartsObject> parts;


        public Truck(String model, String make, int year) {
            super();
            this.model = model;
            this.make = make;
            this.year = year;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public List<TruckPartsObject> getParts() {
            return parts;
        }

        public void setParts(List<TruckPartsObject> parts) {
            this.parts = parts;
        }

        @Override
        public String toString() {
            return "Truck{" +
                    "model='" + model + '\'' +
                    ", make='" + make + '\'' +
                    ", year=" + year +
                    ", parts=" + parts +
                    '}';
        }
    }

    public class TruckPartsObject {

        private String name;
        private String price;
        private HashMap<String, String> partsHashMap;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getPrice() {
            return price;
        }
        public void setPrice(String price) {
            this.price = price;
        }
        public HashMap<String, String> getPartsHashMap() {
            return partsHashMap;
        }
        public void setPartsHashMap(HashMap<String, String> partsHashMap) {
            this.partsHashMap = partsHashMap;
        }

        @Override
        public String toString() {
            return "TruckPartsObject{" +
                    "name='" + name + '\'' +
                    ", price='" + price + '\'' +
                    ", partsHashMap=" + partsHashMap +
                    '}';
        }
    }
}
