package com.lollion.phrasebookservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@FeignClient("dictionary-service")
public interface DictionaryService {

    @GetMapping("/translate")
    public String getTranslation(@NotNull @RequestParam(name = "phrase") String phrase,
                                 @NotNull @RequestParam(name = "from") String from,
                                 @NotNull @RequestParam(name = "to") String to);
}
