package com.duft.shipping_service.domain.enums;

public enum Carriers {

    FEDEX(1, "FedEx"),
    UPS(2, "UPS"),
    INDIAN_POST(3, "Indian Post"),
    BLUE_DART(4, "Blue Dart"),
    DHL(5, "DHL"),
    TNT(6, "TNT");

    private Integer id;
    private String name;

    Carriers(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }



}
