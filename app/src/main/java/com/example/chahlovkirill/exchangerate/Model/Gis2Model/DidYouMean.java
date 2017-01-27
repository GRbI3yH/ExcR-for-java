package com.example.chahlovkirill.exchangerate.Model.Gis2Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by chahlov.kirill on 27/01/17.
 */

public class DidYouMean {
    @SerializedName("rubrics")
    @Expose
    private List<Rubric> rubrics;

    public List<Rubric> getRubrics() {
        return this.rubrics;
    }
    public void setRubrics(List<Rubric> rubrics) {
        this.rubrics = rubrics;
    }
}
