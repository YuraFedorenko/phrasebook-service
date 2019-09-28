package com.lollion.phrasebookservice.controller;

import com.lollion.phrasebookservice.service.PhrasebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
public class PhraseController {

    private final PhrasebookService phrasebookService;

    @Value("${config.phrase}")
    private String configPhrase;

    @GetMapping("/translation")
    public String translate(@NotNull @RequestParam(name = "phrase") String phrase,
                                   @NotNull @RequestParam(name = "from") String from,
                                   @NotNull @RequestParam(name = "to") String to) {
        return phrasebookService.translate(phrase, from, to);
    }

    @RefreshScope
    @GetMapping("/config-translation")
    public String translateFromConfig(@NotNull @RequestParam(name = "from") String from,
                            @NotNull @RequestParam(name = "to") String to) {
        return phrasebookService.translate(configPhrase, from, to);
    }

    @DeleteMapping("/translation")
    public String getAndDelete(@NotNull @RequestParam(name = "phrase") String phrase) {
        return phrasebookService.delete(phrase).getTranslation();
    }
}
