package com.works.repository;

import com.works.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Long> {


    long deleteByIdBetween(long idStart, long idEnd);
}
