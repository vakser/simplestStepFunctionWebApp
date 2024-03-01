package org.example.simpleststepfunctionwebapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepFunction {
    Double b;
    Double initialX;
    Double endX;
    Double step;
}
