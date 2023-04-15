package com.kamil.servicExpert.db.mapper;

import java.util.Date;

import org.springframework.stereotype.Component;
import com.kamil.servicExpert.db.model.Note;
import com.kamil.servicExpert.model.Note.NoteDtoGet;
import com.kamil.servicExpert.model.Note.NoteDtoPost;

@Component
public class NoteMapperImpl implements NoteMapper{

	@Override
	public Note noteInputToNote(NoteDtoPost noteDtoPost) {
		if(noteDtoPost == null) {
			return null;
		}		
		return 	Note
				.builder()
				.description(noteDtoPost.getDescription())
				.dateCreated(new Date())
				.build();
	}

	@Override
	public NoteDtoGet noteToNoteGet(Note note) {
		if(note == null) {
			return null;
		}		
		return 	NoteDtoGet
				.builder()
				.description(note.getDescription())
				.dateCreated(note.getDateCreated())
				.build();
	}

}
