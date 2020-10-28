package uk.co.jimbodev.glasscuttingproblem.to;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class QueryStringParameters {
    String sorted;
    int maxShapes;
    String cuttingAlgorithm;
}
