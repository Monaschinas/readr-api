package com.monaschinas.readr.platform.publishing.domain.persistence;

import com.monaschinas.readr.platform.publishing.domain.model.Chapter;

import java.util.List;
import java.util.Optional;

public interface ChapterRepository {
    Optional<Chapter> findByTitle(String title);
    List<Chapter> findByBookId(Long bookId);
}
