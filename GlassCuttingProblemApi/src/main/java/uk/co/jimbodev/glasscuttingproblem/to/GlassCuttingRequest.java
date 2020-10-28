package uk.co.jimbodev.glasscuttingproblem.to;

import lombok.Data;

@Data
public class GlassCuttingRequest {
    private QueryStringParameters queryStringParameters;
    private String body;
    private Shapes shapes;
}
