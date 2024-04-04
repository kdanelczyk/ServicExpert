package com.kamil.servicExpert.db.mapper;

import com.kamil.servicExpert.db.model.Note;
import com.kamil.servicExpert.model.Note.NoteDtoGet;
import com.kamil.servicExpert.model.Note.NoteDtoPost;

public interface NoteMapper {
	
	Note notePostToNote(NoteDtoPost noteDtoPost);
	
	NoteDtoGet noteToNoteGet(Note note);
	
}
