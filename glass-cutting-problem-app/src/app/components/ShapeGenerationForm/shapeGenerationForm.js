import React from 'react'
import '../../App.css';

const maxHeight = 250
const minHeight = 30
const maxWidth = 300
const minWidth = 35
const maxShapes = 50
const empty = ""
const height = "height"
const width = "width"
const addShapeButtonText = "Add Shape"
const maxShapesErrorMessage = "You can only add 50 shapes"
const pixels = "px"

class ShapeGenerationForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = { 
      width: minWidth,
      height: minHeight,
    };

    this.setErrorsState=this.props.setErrorsState.bind(this)
    this.setShapesState=this.props.setShapesState.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
  }
  
  handleSubmit(event) {
    let newShapes = this.props.shapes
    let updatedErrors = this.props.errors

    if(newShapes.length < maxShapes){
      
      newShapes.push({
        width: this.state.width,
        height: this.state.height
      })
    
      this.setShapesState(newShapes)
    
      updatedErrors.shapesGreaterMax = empty
      this.setErrorsState(updatedErrors)
      
    }
    else{
      updatedErrors.shapesGreaterMax = maxShapesErrorMessage
      this.setErrorsState(updatedErrors)
    }
    
    event.preventDefault();
    }

handleChange = (event) => {
  let name = event.target.name;
  let val = event.target.value;
  if(name === height){
    val = this.validateShapeDimension(val, minHeight, maxHeight)
  }
  else if(name === width){
    val = this.validateShapeDimension(val, minWidth, maxWidth)
  }
  this.setState({[name]: val});
}

validateShapeDimension(val, min, max){
  if(val > max){
    val = max
  }
  else if(val < min){
    val = min 
  }
  return val
}


  displayErrorIfExists(errorName, errorMessage){
    if(this.props.errors[errorName] === errorMessage){
      return (
      <div id="maxShapesError" className="alert alert-danger" role="alert">
        {this.props.errors[errorName]}
      </div>
      )
    }
    else{
      return(<div></div>)
    }
  }

render() {
  return (
          <form id="shapeGenerationForm" onSubmit={this.handleSubmit}>
            <div className="form-group">
              <label htmlFor="heightInput" className="mt-2">Height:</label>
              <input id="heightInput" className="form-control" name={height} type="number" value={this.state.height} onChange={this.handleChange} />
            </div>
            <div className="form-group">
              <label htmlFor="widthInput">Width:</label>
              <input id="widthInput" className="form-control" name={width} type="number" value={this.state.width} onChange={this.handleChange}/>
            </div>
            <input id="addShapeButton" type="submit" value={addShapeButtonText} className="btn btn-primary mb-2" />
            {this.displayErrorIfExists("shapesGreaterMax", maxShapesErrorMessage )} 
          </form>
    )
  }
}
export default ShapeGenerationForm;
