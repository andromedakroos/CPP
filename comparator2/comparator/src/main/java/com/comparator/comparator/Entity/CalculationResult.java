package com.comparator.comparator.Entity;

import javax.persistence.*;

@Entity
@Table(name= "results")

public class CalculationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column (name = "result")
    private Boolean result;

    public CalculationResult(Boolean result) {
        this.result = result;
    }

    public CalculationResult() {

    }
}
