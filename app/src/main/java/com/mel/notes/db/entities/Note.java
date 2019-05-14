package com.mel.notes.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Esto es un decorador
@Entity(tableName = "notas")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int id;

    //Decorador para cambiar nombre de la columna y que sea distinto al nombre del campo
    //@ColumnInfo(name = "titulo")
    public String title;
    public String content;
    public boolean favorite;
    public String color;

    public Note(String title, String content, boolean favorite, String color) {
        this.title = title;
        this.content = content;
        this.favorite = favorite;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
