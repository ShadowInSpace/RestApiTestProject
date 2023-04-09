package go.ti.goItHW12.controller;

import go.ti.goItHW12.entities.Note;
import go.ti.goItHW12.services.NoteService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.HashMap;


@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;


    @GetMapping("/list")
    public ModelAndView getNotes(){
        ModelAndView result = new ModelAndView("notes/listTemplate");
        HashMap<Long,Note> notes = noteService.listAll();
        result.addObject("notes", notes);

        return result;
    }

    @PostMapping("/delete")
    @ResponseBody
    public void deleteNote (@RequestParam("id") int id, HttpServletResponse resp){
    noteService.deleteById(id);
        try {
            resp.sendRedirect("http://localhost:8080/note/list");
            resp.setHeader("Location", "/note/list");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/edit")
    public ModelAndView editeNoteForm (@RequestParam("id") int id){
        ModelAndView result = new ModelAndView("notes/editeNoteTemplate");
        System.out.println(id);
        Note note = noteService.getById(id);
        result.addObject("note", note);
        return result;
    }
    @PostMapping("/edit")
    public void editeNote (HttpServletResponse resp,
                           @RequestParam("id") int id,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content){
        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);
        noteService.update(note);

        try {
            resp.sendRedirect("http://localhost:8080/note/list");
            resp.setHeader("Location", "/note/list");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
