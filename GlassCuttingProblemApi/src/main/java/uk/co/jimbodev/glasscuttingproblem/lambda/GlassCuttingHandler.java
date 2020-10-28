package uk.co.jimbodev.glasscuttingproblem.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uk.co.jimbodev.glasscuttingproblem.exception.InvalidAlgorithmNameException;
import uk.co.jimbodev.glasscuttingproblem.service.BestFitGlassCuttingService;
import uk.co.jimbodev.glasscuttingproblem.service.FirstFitGlassCuttingService;
import uk.co.jimbodev.glasscuttingproblem.service.GlassCuttingService;
import uk.co.jimbodev.glasscuttingproblem.to.GlassCuttingRequest;
import uk.co.jimbodev.glasscuttingproblem.to.GlassCuttingResponseBody;
import uk.co.jimbodev.glasscuttingproblem.to.Shape;
import uk.co.jimbodev.glasscuttingproblem.to.Shapes;

import java.util.Collections;
import java.util.List;

public class GlassCuttingHandler implements RequestHandler<GlassCuttingRequest, APIGatewayProxyResponseEvent> {

    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private static final String BEST_FIT_TITLE = "Best Fit Algorithm";
    private static final String FIRST_FIT_TITLE = "First Fit Algorithm";
    private static final String BEST_FIT_REQUEST_PARAM = "bestFit";
    private static final String FIRST_FIT_REQUEST_PARAM = "firstFit";
    private static final String ALGORITHM_NAME_ERROR_MESSAGE = "Invalid Parameter Value: 'algorithm' - value must be 'bestFit' or 'firstFit'";
    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final int OK_200 = 200;
    private static final int BAD_REQ_400 = 400;
    private static final int START_INDEX = 0;
    private static final int EMPTY = 0;
    private static final String ERROR_JSON = "{\n\t 'error' :";
    private static final String CLOSING_JSON = "\n}";

    public APIGatewayProxyResponseEvent handleRequest(GlassCuttingRequest request, Context context) {

        GlassCuttingResponseBody body = new GlassCuttingResponseBody();
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        request.setShapes(GSON.fromJson(request.getBody(), Shapes.class));
        request.setBody(null);

        sortShapesByArea(request.getShapes().getShapes(), request.getQueryStringParameters().getSorted());

        request.getShapes().setShapes(setSizeOfShapeList(request.getShapes().getShapes(), request.getQueryStringParameters().getMaxShapes()));

        try {
            GlassCuttingService glassCuttingService = initialiseGlassCuttingService(request.getQueryStringParameters().getCuttingAlgorithm());


            body.setTitle(setAlgorithmTitle(request.getQueryStringParameters().getCuttingAlgorithm()));
            body.setShapes(request.getShapes().getShapes());
            body.setSheets(glassCuttingService.putShapesOnShelvesOnSheets(request.getShapes().getShapes()));
            body.setNumberOfSheetsUsed(glassCuttingService.getNumberOfSheets(body.getSheets()));
            body.setAreaUsageEfficiency(glassCuttingService.getSpaceEfficiencyPercentage(body.getSheets()));
            
            response.setBody(GSON.toJson(body));
            response.setStatusCode(OK_200);
        }
        catch (InvalidAlgorithmNameException exception){
            response.setStatusCode(BAD_REQ_400);
            response.setBody(ERROR_JSON + exception.getMessage() + CLOSING_JSON);
        }
        return response;
    }

    private void sortShapesByArea(List<Shape> shapes, String sortingOrder){
        if(shapes != null && sortingOrder != null){
            if(sortingOrder.equals(ASC)){
                Collections.sort(shapes);
            }
            else if(sortingOrder.equals(DESC)){
                Collections.sort(shapes, Collections.reverseOrder());
            }
        }
    }

    private List<Shape> setSizeOfShapeList(List<Shape> shapes, int maxShapes){
        if(shapes != null && maxShapes > EMPTY) {
            if (maxShapes < shapes.size()) {
                shapes = shapes.subList(START_INDEX, maxShapes);
            }
        }
            return shapes;
    }

    private GlassCuttingService initialiseGlassCuttingService(String cuttingAlgorithm){
        GlassCuttingService glassCuttingService = null;
        if(cuttingAlgorithm == null){
            throw new InvalidAlgorithmNameException(ALGORITHM_NAME_ERROR_MESSAGE);
        }
        else {
            if (cuttingAlgorithm.equals(BEST_FIT_REQUEST_PARAM)) {
                glassCuttingService = new BestFitGlassCuttingService();
            } else if (cuttingAlgorithm.equals(FIRST_FIT_REQUEST_PARAM)) {
                glassCuttingService = new FirstFitGlassCuttingService();
            } else {
                throw new InvalidAlgorithmNameException(ALGORITHM_NAME_ERROR_MESSAGE);
            }
        }
        return  glassCuttingService;
    }

    private String setAlgorithmTitle(String cuttingAlgorithm){
        String title = "";
        if(cuttingAlgorithm.equals(BEST_FIT_REQUEST_PARAM)){
            title = BEST_FIT_TITLE;
        }
        else if(cuttingAlgorithm.equals(FIRST_FIT_REQUEST_PARAM)){
            title = FIRST_FIT_TITLE;
        }
        return title;
    }
}
