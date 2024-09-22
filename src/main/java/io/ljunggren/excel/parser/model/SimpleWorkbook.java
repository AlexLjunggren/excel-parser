package io.ljunggren.excel.parser.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleWorkbook {

    private String name;
    private List<SimpleSheet> sheets = new ArrayList<>();
    
}
