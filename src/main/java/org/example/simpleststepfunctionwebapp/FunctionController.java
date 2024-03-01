package org.example.simpleststepfunctionwebapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if (function.getInitialX() != null && function.getEndX() != null && function.getStep() != null && function.getB() != null) {
            model.addAttribute("min", functionService.getMinValueOfY(function));
            model.addAttribute("max", functionService.getMaxValueOfY(function));
            double[] xValues = functionService.createXArray(function);
            double[] yValues = functionService.createYArray(function);
            Map<Double, Double> values = new TreeMap<>();
            for (int i = 0; i < xValues.length; i++) {
                values.put(xValues[i], yValues[i]);
            }
            model.addAttribute("values", values);
        }
        return "home";
    }

    @PostMapping("/")
    public String calculate(StepFunction function, RedirectAttributes attributes) {
        functionService.getMinValueOfY(function);
        functionService.getMaxValueOfY(function);
        attributes.addFlashAttribute(function);
        return "redirect:/";
    }

}
