package io.ljunggren.excel.parser.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleRow {

    private List<SimpleCell> cells = new ArrayList<>();
    
}
