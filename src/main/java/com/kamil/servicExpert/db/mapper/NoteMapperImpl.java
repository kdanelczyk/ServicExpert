package com.kamil.servicExpert.db.mapper;

import java.util.Date;

import org.springframework.stereotype.Component;
import com.kamil.servicExpert.db.model.Note;
import com.kamil.servicExpert.model.Note.NoteGet;
import com.kamil.servicExpert.model.Note.NotePost;

@Component
public class NoteMapperImpl implements NoteMapper{

	@Override
	public Note noteInputToNote(NotePost notePost) {
		if(notePost == null) {
			return null;
		}		
		return 	Note.builder()
				.description(notePost.getDescription())
				.dateCreated(new Date())
				.build();
	}

	@Override
	public NoteGet noteToNoteGet(Note note) {
		if(note == null) {
			return null;
		}		
		return 	NoteGet.builder()
				.description(note.getDescription())
				.dateCreated(note.getDateCreated())
				.build();
	}

}
