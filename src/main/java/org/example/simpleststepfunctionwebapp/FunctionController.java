package org.example.simpleststepfunctionwebapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.TreeMap;

@Controller
public class FunctionController {
    private final FunctionService functionService;

    public FunctionController(FunctionService functionService) {
        this.functionService = functionService;
    }

    @GetMapping("/")
    public String homePage(StepFunction function, Model model) {
        model.addAttribute("function", function);
        return "home";
    }

    @PostMapping("/result")
    public String calculate(StepFunction function, Model model) {
        model.addAttribute("function", function);
        model.addAttribute("min", functionService.getMinValueOfY(function));
        model.addAttribute("max", functionService.getMaxValueOfY(function));
        double[] xValues = functionService.createXArray(function);
        double[] yValues = functionService.createYArray(function);
        Map<Integer, String> values = new TreeMap<>();
        for (int i = 0; i < xValues.length; i++) {
            values.put(i, xValues[i] + " " + yValues[i]);
        }
        model.addAttribute("values", values);
        return "result";
    }

}
