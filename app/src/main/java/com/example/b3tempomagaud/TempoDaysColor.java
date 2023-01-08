package com.example.b3tempomagaud;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempoDaysColor {
    @SerializedName("couleurJourJ")
    @Expose
    private TempoColor couleurJourJ;
    @SerializedName("couleurJourJ1")
    @Expose
    private TempoColor couleurJourJ1;

    public TempoColor getcouleurJourJ() {
        return couleurJourJ;
    }

    public void setcouleurJourJ(TempoColor couleurJourJ) {
        this.couleurJourJ = couleurJourJ;
    }

    public TempoColor getcouleurJourJ1() {
        return couleurJourJ1;
    }

    public void setcouleurJourJ1(TempoColor couleurJourJ1) {
        this.couleurJourJ1 = couleurJourJ1;
    }
}
