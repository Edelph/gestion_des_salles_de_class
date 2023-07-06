package com.edelph.jhon.gestion_salle.util;

import com.edelph.jhon.gestion_salle.entity.Genre;
import jakarta.persistence.AttributeConverter;

public class GenreConverter implements AttributeConverter<Genre, Character> {

    @Override
    public Character convertToDatabaseColumn(Genre attribute) {
        if (attribute == null) return null;
        return Genre.FEMININ.getCode();
    }

    @Override
    public Genre convertToEntityAttribute(Character dbData) {
        if (dbData == null) return null;
        return Genre.fromCode(dbData);
    }
}
