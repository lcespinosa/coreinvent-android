package com.coresolutions.coreinvent.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Characteristics implements Serializable {

    @SerializedName("Importado")
    @Expose
    private String importado;
    private final static long serialVersionUID = 974862358148811782L;

    public String getImportado() {
        return importado;
    }

    public void setImportado(String importado) {
        this.importado = importado;
    }

}
