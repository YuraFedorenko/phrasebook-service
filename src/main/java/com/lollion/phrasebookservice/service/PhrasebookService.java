package com.lollion.phrasebookservice.service;

import com.lollion.phrasebookservice.model.PhraseEntity;

public interface PhrasebookService {

    String translate(String phrase, String langFrom, String langTo);

    PhraseEntity delete(String phrase);
}
