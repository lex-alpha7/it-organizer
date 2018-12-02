package ru.akhitev.organizer.db.entity;

public interface NodeDataBaseEntity<R extends DataBaseEntity> extends DataBaseEntity {
    void setRoot(R root);
}
