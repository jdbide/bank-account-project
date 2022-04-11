package com.jdbide.domain.model.bankaccount.domain.model;

/**
 * @author jdbide
 */
public enum BankOperationType {

    INIT("Creation du compte"), DEPOSIT("Depot"), WITHDRAW("Retrait");

    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    BankOperationType(String s) {
        this.libelle = s;
    }
}
