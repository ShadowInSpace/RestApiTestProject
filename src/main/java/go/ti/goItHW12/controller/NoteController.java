package go.ti.goItHW12.controller;

import go.ti.goItHW12.entities.Note;
import go.ti.goItHW12.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;


    @GetMapping("/list")
    public ModelAndView getNotes(NoteService noteService){
        ModelAndView result = new ModelAndView("notes/listTemplate");
        List<Note> notes = noteService.listAll();
        //for testing
        Note note = new Note();
        note.setTitle("Test Note");
        note.setContext("Some Test Context");
        notes.add(note);
        //end of test code

        result.addObject("notes", notes);


        return result;
    }
}
