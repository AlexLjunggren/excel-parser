package ljunggren.io.excel.parser.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleSheet {
    
    private String name;
    private SimpleRow headers = new SimpleRow();
    private List<SimpleRow> rows = new ArrayList<>();
    
}
