package com.dictionary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class DictionaryController {

    @Autowired
    private ObjectMapper objectMapper;
    private final WebClient webClient=WebClient.create();

    @InitBinder
    public void initBinder(WebDataBinder dataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }


    @GetMapping("/")
    public String myHome(Model theModel)
    {

        theModel.addAttribute("current",new myModel());
        return "myhome";
    }

    @PostMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("current") myModel current, BindingResult theBindingResult,Model theModel) throws JsonProcessingException {
        if(theBindingResult.hasErrors())
        {
            return "myhome";
        }

        String Json;
        try{
    Json = webClient.get()
            .uri("https://api.dictionaryapi.dev/api/v2/entries/en/" + current.getWord())
            .retrieve()
            .bodyToMono(String.class)
            .block();}
        catch(Exception exc)
        {
            return "invalid";
        }


    Element[] element = objectMapper.readValue(Json, Element[].class);

    Element currentElement = new Element();

    for (Element temp : element) {
        currentElement = temp;
    }


    theModel.addAttribute("element", currentElement);

    return "display";



    }




}
