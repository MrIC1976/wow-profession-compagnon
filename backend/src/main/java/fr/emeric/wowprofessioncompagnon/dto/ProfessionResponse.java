package fr.emeric.wowprofessioncompagnon.dto;

/**
 * ============================================================================
 * Fichier : ProfessionResponse.java
 * Projet : WoW Profession Compagnon
 * Auteur : Emeric
 * ----------------------------------------------------------------------------
 * DTO représentant un métier de World of Warcraft.
 *
 * Cette classe est envoyée au frontend Angular.
 *
 * Elle ne représente pas une entité de base de données.
 * ============================================================================
 */
public class ProfessionResponse {

    /**
     * Identifiant Blizzard.
     */
    private Integer id;

    /**
     * Nom du métier.
     */
    private String name;

    /**
     * URL de l'icône.
     */
    private String icon;

    /**
     * Constructeur vide.
     */
    public ProfessionResponse() {
    }

    /**
     * Constructeur complet.
     */
    public ProfessionResponse(Integer id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}