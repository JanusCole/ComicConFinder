package com.example.janus.confinder.data;

public class Convention {

    private String loc;
    private Double longitude;
    private String name;
    private String dates;
    private Integer id;
    private String type;
    private String urlname;
    private Double latitude;
    private String status;

    public Convention(String loc,
                      Double longitude,
                      String name,
                      String dates,
                      Integer id,
                      String type,
                      String urlname,
                      Double latitude,
                      String status) {
        this.loc = loc;
        this.longitude = longitude;
        this.name = name;
        this.dates = dates;
        this.id = id;
        this.type = type;
        this.urlname = urlname;
        this.latitude = latitude;
        this.status = status;
    }

    public String getLoc() {
        return loc;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getDates() {
        return dates;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUrlname() {
        return urlname;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return name + " " + loc + " " + dates;
    }
}
