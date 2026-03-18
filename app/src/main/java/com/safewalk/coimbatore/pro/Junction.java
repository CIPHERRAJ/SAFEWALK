package com.safewalk.coimbatore.pro;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "junctions")
public class Junction {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String safetyScore;
    private String details;
    private String tamilDetails;
    private double latitude;
    private double longitude;

    public Junction(String name, String safetyScore, String details, String tamilDetails, double latitude, double longitude) {
        this.name = name;
        this.safetyScore = safetyScore;
        this.details = details;
        this.tamilDetails = tamilDetails;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public String getSafetyScore() { return safetyScore; }
    public String getDetails() { return details; }
    public String getTamilDetails() { return tamilDetails; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}
