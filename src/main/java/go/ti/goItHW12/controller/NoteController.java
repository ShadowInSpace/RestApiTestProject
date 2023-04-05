package go.ti.goItHW12.controller;

import go.ti.goItHW12.entities.Note;
import go.ti.goItHW12.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;


    @GetMapping("/list")
    public ModelAndView getNotes(NoteService noteService){
        ModelAndView result = new ModelAndView("list/listTemplate");
        List<Note> notes = noteService.listAll();
        //for testing
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContent("Some Test Context");
        notes.add(note);
        Note note2 = new Note();
        note2.setTitle("Test Note2");
        note2.setContent("Some Test Context2");
        notes.add(note2);
        //end of test code
        result.addObject("notes", notes);

        return result;
    }

    @PostMapping("/delete/{id}")
    public void deleteNote (@PathVariable int id){


    }
}
