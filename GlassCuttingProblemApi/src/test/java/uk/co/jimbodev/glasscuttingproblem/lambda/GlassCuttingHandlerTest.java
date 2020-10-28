package uk.co.jimbodev.glasscuttingproblem.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import uk.co.jimbodev.glasscuttingproblem.testutils.MockContext;
import uk.co.jimbodev.glasscuttingproblem.to.GlassCuttingRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GlassCuttingHandlerTest {
    private static final Gson GSON =  new GsonBuilder().create();

    private static final String BEST_FIT_REQUEST = "{\"queryStringParameters\":{\"cuttingAlgorithm\":\"bestFit\"},\"body\":\"{\'shapes\':[{\'width\':200,\'height\':150},{\'width\':100,\'height\':100}]}\"}";
    private static final String BEST_FIT_RESPONSE_BODY = "{\"title\":\"Best Fit Algorithm\",\"numberOfSheetsUsed\":1,\"areaUsageEfficiency\":0.53,\"sheets\":[{\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":200,\"height\":150,\"insertionNumber\":1},{\"x1\":200,\"y1\":0,\"width\":100,\"height\":100,\"insertionNumber\":2}]}],\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":200,\"height\":150,\"insertionNumber\":1},{\"x1\":200,\"y1\":0,\"width\":100,\"height\":100,\"insertionNumber\":2}]}";
    private static final String FIRST_FIT_RESPONSE_BODY = "{\"title\":\"First Fit Algorithm\",\"numberOfSheetsUsed\":1,\"areaUsageEfficiency\":0.53,\"sheets\":[{\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":200,\"height\":150,\"insertionNumber\":1},{\"x1\":0,\"y1\":150,\"width\":100,\"height\":100,\"insertionNumber\":2}]}],\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":200,\"height\":150,\"insertionNumber\":1},{\"x1\":0,\"y1\":150,\"width\":100,\"height\":100,\"insertionNumber\":2}]}";
    private static final String FIRST_FIT_REQUEST = "{\"queryStringParameters\":{\"cuttingAlgorithm\":\"firstFit\"},\"body\":\"{\'shapes\':[{\'width\':200,\'height\':150},{\'width\':100,\'height\':100}]}\"}";
    private static final String INVALID_ALGORITHM_REQUEST = "{\"queryStringParameters\":{\"cuttingAlgorithm\":\"firstFitAlgo\"},\"body\":\"{\'shapes\':[{\'width\':200,\'height\':150},{\'width\':100,\'height\':100}]}\"}";
    private static final String INVALID_ALGORITHM_RESPONSE = "{\n\t 'error' :Invalid Parameter Value: 'algorithm' - value must be 'bestFit' or 'firstFit'\n}";
    private static final String BEST_FIT_REQUEST_ASCENDING = "{\"queryStringParameters\":{\"cuttingAlgorithm\":\"bestFit\", \"sorted\":\"asc\"},\"body\":\"{\'shapes\':[{\'width\':200,\'height\':150},{\'width\':100,\'height\':100}]}\"}";
    private static final String BEST_FIT_RESPONSE_ASCENDING = "{\"title\":\"Best Fit Algorithm\",\"numberOfSheetsUsed\":1,\"areaUsageEfficiency\":0.53,\"sheets\":[{\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":100,\"height\":100,\"insertionNumber\":1},{\"x1\":0,\"y1\":100,\"width\":200,\"height\":150,\"insertionNumber\":2}]}],\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":100,\"height\":100,\"insertionNumber\":1},{\"x1\":0,\"y1\":100,\"width\":200,\"height\":150,\"insertionNumber\":2}]}";
    private static final String BEST_FIT_REQUEST_DESCENDING = "{\"queryStringParameters\":{\"cuttingAlgorithm\":\"bestFit\", \"sorted\":\"desc\"},\"body\":\"{\'shapes\':[{\'width\':200,\'height\':150},{\'width\':100,\'height\':100}]}\"}";
    private static final String BEST_FIT_RESPONSE_DESCENDING = "{\"title\":\"Best Fit Algorithm\",\"numberOfSheetsUsed\":1,\"areaUsageEfficiency\":0.53,\"sheets\":[{\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":200,\"height\":150,\"insertionNumber\":1},{\"x1\":200,\"y1\":0,\"width\":100,\"height\":100,\"insertionNumber\":2}]}],\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":200,\"height\":150,\"insertionNumber\":1},{\"x1\":200,\"y1\":0,\"width\":100,\"height\":100,\"insertionNumber\":2}]}";
    private static final String MAX_SHAPE_LESS_THAN_NUMBER_OF_SHAPES = "{\"queryStringParameters\":{\"cuttingAlgorithm\":\"bestFit\", \"sorted\":\"desc\", \"maxShapes\":\"1\"},\"body\":\"{\'shapes\':[{\'width\':200,\'height\':150},{\'width\':100,\'height\':100}]}\"}";
    private static final String MAX_SHAPE_LESS_THAN_NUMBER_OF_SHAPES_RESPONSE = "{\"title\":\"Best Fit Algorithm\",\"numberOfSheetsUsed\":1,\"areaUsageEfficiency\":0.4,\"sheets\":[{\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":200,\"height\":150,\"insertionNumber\":1}]}],\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":200,\"height\":150,\"insertionNumber\":1}]}";
    private static final String MAX_SHAPE_GREATER_THAN_NUMBER_OF_SHAPES = "{\"queryStringParameters\":{\"cuttingAlgorithm\":\"bestFit\", \"sorted\":\"desc\", \"maxShapes\":\"3\"},\"body\":\"{\'shapes\':[{\'width\':200,\'height\':150},{\'width\':100,\'height\':100}]}\"}";
    private static final String MAX_SHAPE_GREATER_THAN_NUMBER_OF_SHAPES_RESPONSE = "{\"title\":\"Best Fit Algorithm\",\"numberOfSheetsUsed\":1,\"areaUsageEfficiency\":0.53,\"sheets\":[{\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":200,\"height\":150,\"insertionNumber\":1},{\"x1\":200,\"y1\":0,\"width\":100,\"height\":100,\"insertionNumber\":2}]}],\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":200,\"height\":150,\"insertionNumber\":1},{\"x1\":200,\"y1\":0,\"width\":100,\"height\":100,\"insertionNumber\":2}]}";
    private static final String MAX_SHAPE_EQ_THAN_NUMBER_OF_SHAPES = "{\"queryStringParameters\":{\"cuttingAlgorithm\":\"bestFit\", \"sorted\":\"desc\", \"maxShapes\":\"2\"},\"body\":\"{\'shapes\':[{\'width\':200,\'height\':150},{\'width\':100,\'height\':100}]}\"}";
    private static final String MAX_SHAPE_EQ_THAN_NUMBER_OF_SHAPES_RESPONSE = "{\"title\":\"Best Fit Algorithm\",\"numberOfSheetsUsed\":1,\"areaUsageEfficiency\":0.53,\"sheets\":[{\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":200,\"height\":150,\"insertionNumber\":1},{\"x1\":200,\"y1\":0,\"width\":100,\"height\":100,\"insertionNumber\":2}]}],\"shapes\":[{\"x1\":0,\"y1\":0,\"width\":200,\"height\":150,\"insertionNumber\":1},{\"x1\":200,\"y1\":0,\"width\":100,\"height\":100,\"insertionNumber\":2}]}";

    @Test
    public void testBestFitRequestReturnsOutputUsingBestFitAlgorithm() {
        GlassCuttingRequest request = GSON.fromJson(BEST_FIT_REQUEST, GlassCuttingRequest.class);
        APIGatewayProxyResponseEvent response = new GlassCuttingHandler().handleRequest(request, new MockContext());

        assertEquals(BEST_FIT_RESPONSE_BODY,response.getBody());
        assertEquals(200, response.getStatusCode());
    }


    @Test
    public void testFirstFitRequestReturnsOutputUsingFirstFitAlgorithm(){
        GlassCuttingRequest request = GSON.fromJson(FIRST_FIT_REQUEST, GlassCuttingRequest.class);
        APIGatewayProxyResponseEvent response = new GlassCuttingHandler().handleRequest(request, new MockContext());

        assertEquals(FIRST_FIT_RESPONSE_BODY,response.getBody());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testInvalidCuttingAlgorithmReturnsErrorCode(){
        GlassCuttingRequest request = GSON.fromJson(INVALID_ALGORITHM_REQUEST, GlassCuttingRequest.class);

        APIGatewayProxyResponseEvent response = new GlassCuttingHandler().handleRequest(request, new MockContext());

        assertEquals(INVALID_ALGORITHM_RESPONSE, response.getBody());
        assertEquals(400, response.getStatusCode());

    }

    @Test
    public void testBestFitRequestReturnsShapesInAscendingOrder(){
        GlassCuttingRequest request = GSON.fromJson(BEST_FIT_REQUEST_ASCENDING, GlassCuttingRequest.class);
        APIGatewayProxyResponseEvent response = new GlassCuttingHandler().handleRequest(request, new MockContext());

        assertEquals(BEST_FIT_RESPONSE_ASCENDING, response.getBody());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testBestFitRequestReturnsShapesInDescendingOrder(){
        GlassCuttingRequest request = GSON.fromJson(BEST_FIT_REQUEST_DESCENDING, GlassCuttingRequest.class);
        APIGatewayProxyResponseEvent response = new GlassCuttingHandler().handleRequest(request, new MockContext());

        assertEquals(BEST_FIT_RESPONSE_DESCENDING, response.getBody());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testBestFitRequestReturnsOnly1ShapeInList(){
        GlassCuttingRequest request = GSON.fromJson(MAX_SHAPE_LESS_THAN_NUMBER_OF_SHAPES, GlassCuttingRequest.class);
        APIGatewayProxyResponseEvent response = new GlassCuttingHandler().handleRequest(request, new MockContext());

        assertEquals(MAX_SHAPE_LESS_THAN_NUMBER_OF_SHAPES_RESPONSE, response.getBody());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testBestFitRequestReturnsAll2ShapeInListWhenMaxShapesGreaterThanNumberOfShapes(){
        GlassCuttingRequest request = GSON.fromJson(MAX_SHAPE_GREATER_THAN_NUMBER_OF_SHAPES, GlassCuttingRequest.class);
        APIGatewayProxyResponseEvent response = new GlassCuttingHandler().handleRequest(request, new MockContext());

        assertEquals(MAX_SHAPE_GREATER_THAN_NUMBER_OF_SHAPES_RESPONSE, response.getBody());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testBestFitRequestReturnsAll2ShapeInListWhenMaxShapesEqualsNumberOfShapesInList(){
        GlassCuttingRequest request = GSON.fromJson(MAX_SHAPE_EQ_THAN_NUMBER_OF_SHAPES, GlassCuttingRequest.class);
        APIGatewayProxyResponseEvent response = new GlassCuttingHandler().handleRequest(request, new MockContext());

        assertEquals(MAX_SHAPE_EQ_THAN_NUMBER_OF_SHAPES_RESPONSE, response.getBody());
        assertEquals(200, response.getStatusCode());
    }
}
