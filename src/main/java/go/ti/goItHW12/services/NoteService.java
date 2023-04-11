package go.ti.goItHW12.services;

import go.ti.goItHW12.entities.Note;
import go.ti.goItHW12.repositories.NoteRepository;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;


@Data
@Service
@RequiredArgsConstructor
public class NoteService {
    @NonNull
    private final HashMap<Long,Note> storage;
    private final NoteRepository repository;

    public ArrayList<Note> listAll() {
        return (ArrayList<Note>) repository.findAll();
    }

    public Note add(Note note) {

        return repository.save(note);
    }

    public void deleteById(long id) {
        try {
            repository.deleteById(id);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("No such note exists");
        }
    }

    public void update(Note note) {
        Optional<Note> origin = repository.findById(note.getId());
        if(origin.isEmpty())throw new IllegalArgumentException("No such note exists");
        origin.get().setTitle(note.getTitle());
        origin.get().setContent(note.getContent());
        repository.save(origin.get());
    }

    public Note getById(long id) {
        Optional<Note> note = repository.findById(id);
        if(note.isEmpty())throw new IllegalArgumentException("No such note exists");
        return note.get();
    }
}
