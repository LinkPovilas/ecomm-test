package web.object.reference;

public enum Region {
    VILNIAUS("Vilniaus"),
    KAUNO("Kauno"),
    KLAIPĖDOS("Klaipėdos"),
    ŠIAULIŲ("Šiaulių"),
    PANEVĖŽIO("Panevėžio"),
    ALYTAUS("Alytaus"),
    MARIJAMPOLĖS("Marijampolės"),
    UTENOS("Utenos"),
    TELŠIŲ("Telšių"),
    TAURAGĖS("Tauragės");

    public final String value;

    Region(String region) {
        this.value = region;
    }

    public String getValue() {
        return value;
    }
}
