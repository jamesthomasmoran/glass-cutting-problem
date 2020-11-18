const requestURL = "https://8gpl2kxew8.execute-api.eu-west-2.amazonaws.com/Prod/sheetofglasswithshapes"

function handleApiCall(params, shapes, updateOutputState, updateShapesState){
    sendRequest(params, shapes, updateOutputState, updateShapesState)
}
function handleResponse(request, updateOutputState, updateShapesState){
    if (request.readyState == 4 && request.status == 200) {
        console.log(request.responseText)
        const response = JSON.parse(request.responseText)
        updateOutputState(response.title, response.sheets, response.numberOfSheetsUsed, response.areaUsageEfficiency)
        updateShapesState(response.shapes)
    }
}

function sendRequest(params, shapes, updateOutputState, updateShapesState){
    
    let request = new XMLHttpRequest();
    request.open("POST", requestURL + formatParams(params), true);
    request.setRequestHeader("Content-Type", "application/json")
    
    request.onreadystatechange = () => {
            handleResponse(request, updateOutputState, updateShapesState)
    }
        
    const shapesJson = {
        shapes: shapes
    }

     request.send(JSON.stringify(shapesJson))
} 

function formatParams(params){
    return "?" + Object
          .keys(params)
          .map(function(key){
            return key+"="+encodeURIComponent(params[key])
          })
          .join("&")
  }

export default handleApiCall