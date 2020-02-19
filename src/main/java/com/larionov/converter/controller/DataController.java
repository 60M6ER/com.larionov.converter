package com.larionov.converter.controller;

import com.larionov.converter.entities.ConverterForm;
import com.larionov.converter.entities.Currency;
import com.larionov.converter.repository.CurrencyRepo;
import com.larionov.converter.services.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class DataController {

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private Converter converter;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("/")
    public String home(Model model) {
        String nameUser = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            if (userDetails != null)
                nameUser = userDetails.getUsername();
        }
        model.addAttribute("nameUser", nameUser);
        ConverterForm converterForm = new ConverterForm();
        converterForm.setDate(dateFormat.format(new Date()));
        converterForm.setCurrencies((ArrayList<Currency>) currencyRepo.findAll());
        model.addAttribute("converterForm", converterForm);
        model.addAttribute("onHistory", false);
        return "index";
    }

    @PostMapping("/")
    public String convert(@ModelAttribute ConverterForm converterForm, Model model) {
        converterForm.setCurrencies((ArrayList<Currency>) currencyRepo.findAll());
        converter.execute(converterForm);
        model.addAttribute(converterForm);
        return "index";
    }

    @GetMapping("/history")
    public String history(boolean onHistory, Model model) {
        model.addAttribute("onHistory", !onHistory);
        return "index";
    }
}
