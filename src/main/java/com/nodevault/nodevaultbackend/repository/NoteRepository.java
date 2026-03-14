package com.nodevault.nodevaultbackend.repository;

import com.nodevault.nodevaultbackend.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUserId(Long userId);

    List<Note> findByUserIdAndPinnedTrue(Long userId);

    List<Note> findByUserIdAndFavoriteTrue(Long userId);

    List<Note> findByUserIdAndTitleContainingIgnoreCase(Long userId, String q);
}