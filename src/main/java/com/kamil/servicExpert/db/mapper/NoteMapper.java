package com.kamil.servicExpert.db.mapper;

import org.mapstruct.Mapper;

import com.kamil.servicExpert.db.model.Note;
import com.kamil.servicExpert.model.Note.NoteGet;
import com.kamil.servicExpert.model.Note.NotePost;

@Mapper(componentModel = "spring")
public interface NoteMapper {
	
	Note noteInputToNote(NotePost notePost);
	
	NoteGet noteToNoteGet(Note note);
}
