package com.mel.notes.interfaces;

import com.mel.notes.pojos.Note;

public interface NotesInteractionListener {
    void editNoteClick(Note note);
    void removeNoteClick(Note note);
    void favoriteNoteClick(Note note);
}
