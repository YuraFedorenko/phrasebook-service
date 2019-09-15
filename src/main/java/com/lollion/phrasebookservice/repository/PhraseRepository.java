package com.lollion.phrasebookservice.repository;

import com.lollion.phrasebookservice.model.PhraseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhraseRepository extends JpaRepository<PhraseEntity, Long> {

    PhraseEntity deletePhraseEntityByPhrase(String phrase);

    boolean existsByPhraseAndTranslation(String phrase, String translation);
}
