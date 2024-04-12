package business.entity;

import java.io.Serializable;

public class Catalog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String catalogId;
    private String catalogName;
    private String description;
    private boolean status;

    public Catalog() {
    }

    public Catalog(String catalogId, String catalogName, String description) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.status = true;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String red = "\u001B[31m";
        String reset = "\u001B[0m";
        return red + "Catalog{" +
                "catalogId='" + catalogId + '\'' +
                ", catalogName='" + catalogName + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}' + reset;
    }
}