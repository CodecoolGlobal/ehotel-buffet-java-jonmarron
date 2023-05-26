package com.codecool.ehotel.model;

public enum GuestNames {

    THORIN("dwarf"),
    DORI("dwarf"),
    NORI("dwarf"),
    ORI("dwarf"),
    BALIN("dwarf"),
    DWALIN("dwarf"),
    FILI("dwarf"),
    KILI("dwarf"),
    ÓIN("dwarf"),
    GLÓIN("dwarf"),
    BIFUR("dwarf"),
    BOFUR("dwarf"),
    BOMBUR("dwarf"),
    GALADRIEL("elf"),
    LEGOLAS("elf"),
    THRANDUIL("elf"),
    ARWEN("elf"),
    EARNDIL("elf"),
    GLORFINDEL("elf"),
    ELROND("elf"),
    LÚTHIEN("elf"),
    GILGALAD("elf"),
    CELEBRIMBOR("elf"),
    ISILDUR("human"),
    BOROMIR("human"),
    FARAMIR("human"),
    ÉOMER("human"),
    ELENDIL("human"),
    HURIN("human"),
    TURIN("human"),
    EOWYN("human");

    private final String type;

    GuestNames(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
