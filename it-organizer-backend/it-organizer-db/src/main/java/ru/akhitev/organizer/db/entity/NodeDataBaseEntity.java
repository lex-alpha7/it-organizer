package ru.akhitev.organizer.db.entity;

public interface NodeDataBaseEntity<R extends DataBaseEntity> extends DataBaseEntity {
    Integer getId();
    void setRoot(R root);
}
