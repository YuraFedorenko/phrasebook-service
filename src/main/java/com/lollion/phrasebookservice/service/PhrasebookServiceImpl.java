package com.lollion.phrasebookservice.service;

import com.lollion.phrasebookservice.model.Language;
import com.lollion.phrasebookservice.model.MailEvent;
import com.lollion.phrasebookservice.model.MailType;
import com.lollion.phrasebookservice.model.PhraseEntity;
import com.lollion.phrasebookservice.repository.PhraseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Collections;

import static com.lollion.phrasebookservice.model.MailType.TRANSLATION;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class PhrasebookServiceImpl implements PhrasebookService {

    private final DictionaryService dictionaryService;
    private final PhraseRepository phraseRepository;
    private final KafkaTemplate<String, MailEvent> kafkaTemplate;

    @Value("${app.topic.mail}")
    private String topic;

    @Value("${app.participant.email}")
    private String participant;

    @Override
    @Cacheable(key = "#phrase", value = "phrases")
    public String translate(String phrase, String langFrom, String langTo) {
        log.info("Returning from google cloud translate!");
        String translation = dictionaryService.getTranslation(phrase, langFrom, langTo);

        // send email just for practice.
        sendMail(translation, participant);

        if (phraseRepository.existsByPhraseAndTranslation(phrase, translation)){
            return translation;
        }
        return phraseRepository.save(PhraseEntity.builder()
                .phrase(phrase)
                .translation(translation)
                .languageFrom(Language.valueOf(langFrom))
                .languageTo(Language.valueOf(langTo))
                .build()).getTranslation();
    }

    @Override
    @CacheEvict(key = "#phrace")
    public PhraseEntity delete(String phrase) {
       return phraseRepository.deletePhraseEntityByPhrase(phrase);
    }

    private void sendMail(String phrase, String participant){
        kafkaTemplate.send(topic, MailEvent.builder()
                .type(TRANSLATION)
                .metadata(Collections.singletonMap("phrase", phrase))
                .participants(Collections.singleton(participant))
                .build());
    }
}
