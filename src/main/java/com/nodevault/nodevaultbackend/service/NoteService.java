package com.nodevault.nodevaultbackend.service;

import com.nodevault.nodevaultbackend.dto.NoteRequest;
import com.nodevault.nodevaultbackend.entity.Note;
import com.nodevault.nodevaultbackend.entity.User;
import com.nodevault.nodevaultbackend.repository.NoteRepository;
import com.nodevault.nodevaultbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepo;
    private final UserRepository userRepo;

    // ✅ CREATE BY EMAIL (JWT)
    public Note createByEmail(String email, NoteRequest req) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = Note.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .pinned(req.isPinned())
                .favorite(req.isFavorite())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        return noteRepo.save(note);
    }

    // ✅ GET ALL BY EMAIL
    public List<Note> getAllByEmail(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return noteRepo.findByUserId(user.getId());
    }

    // ✅ UPDATE
    public Note update(Long id, NoteRequest req) {

        Note note = noteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        note.setTitle(req.getTitle());
        note.setContent(req.getContent());
        note.setPinned(req.isPinned());
        note.setFavorite(req.isFavorite());

        return noteRepo.save(note);
    }

    // ✅ DELETE
    public void delete(Long id) {
        noteRepo.deleteById(id);
    }

    // ✅ SEARCH BY EMAIL
    public List<Note> searchByEmail(String email, String q) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return noteRepo.findByUserIdAndTitleContainingIgnoreCase(user.getId(), q);
    }
}