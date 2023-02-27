package com.kamil.servicExpert.db.mapper;

import org.mapstruct.Mapper;

import com.kamil.servicExpert.db.model.Note;
import com.kamil.servicExpert.model.Note.NoteDtoGet;
import com.kamil.servicExpert.model.Note.NoteDtoPost;

@Mapper(componentModel = "spring")
public interface NoteMapper {
	
	Note noteInputToNote(NoteDtoPost noteDtoPost);
	
	NoteDtoGet noteToNoteGet(Note note);
}
