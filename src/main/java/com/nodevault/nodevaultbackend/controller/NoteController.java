package com.nodevault.nodevaultbackend.controller;

import com.nodevault.nodevaultbackend.dto.NoteRequest;
import com.nodevault.nodevaultbackend.entity.Note;
import com.nodevault.nodevaultbackend.service.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class NoteController {

    private final NoteService service;

    // ✅ CREATE NOTE
    @PostMapping
    public Note create(HttpServletRequest request,
                       @RequestBody NoteRequest req) {

        String email = (String) request.getUserPrincipal().getName();
        return service.createByEmail(email, req);
    }

    // ✅ GET ALL NOTES
    @GetMapping
    public List<Note> getAll(HttpServletRequest request) {

        String email = (String) request.getUserPrincipal().getName();
        return service.getAllByEmail(email);
    }

    // ✅ UPDATE NOTE
    @PutMapping("/{id}")
    public Note update(@PathVariable Long id,
                       @RequestBody NoteRequest req) {
        return service.update(id, req);
    }

    // ✅ DELETE NOTE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ✅ SEARCH
    @GetMapping("/search")
    public List<Note> search(HttpServletRequest request,
                             @RequestParam String q) {

        String email = (String) request.getUserPrincipal().getName();
        return service.searchByEmail(email, q);
    }
}