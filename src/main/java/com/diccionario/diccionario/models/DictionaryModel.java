package com.diccionario.diccionario.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.util.List;

@EntityScan
public class DictionaryModel implements Serializable {

    private static final long serialVersionUID = -3942918535912514249L;
    private List<TermModel> terms;

    public DictionaryModel() {
    }

    public List<TermModel> getTerms() {
        return terms;
    }

    public void setTerms(List<TermModel> terms) {
        this.terms = terms;
    }
}
