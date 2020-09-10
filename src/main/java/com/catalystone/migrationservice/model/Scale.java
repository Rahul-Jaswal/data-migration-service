package com.catalystone.migrationservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "SCALE")
public class Scale {
    @Id
    private int ID;

    private UUID GUID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public UUID getGUID() {
        return GUID;
    }

    public void setGUID(UUID GUID) {
        this.GUID = GUID;
    }
}
