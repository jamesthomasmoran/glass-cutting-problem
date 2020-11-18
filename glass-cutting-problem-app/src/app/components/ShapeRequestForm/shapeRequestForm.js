import React from 'react'
import '../../App.css';
import AlgorithmParameterForm from '../AlgorithmParameterForm/algorithmParameterForm'
import ShapeDisplay from '../ShapeDisplay/shapeDisplay'
import ShapeGenerationForm from '../ShapeGenerationForm/shapeGenerationForm'

const empty = ""

class ShapeRequestForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      shapes: [],
      errors:{
        shapesGreaterMax: empty,
        selectCuttingAlgorithm: empty
      },
    };
    this.setShapesState = this.setShapesState.bind(this)
    this.setErrorsState = this.setErrorsState.bind(this)
    this.setOutputState = this.props.setOutputState.bind(this)

  }
  
setShapesState(shapes){
    this.setState({"shapes": shapes})
}
setErrorsState(errors){
    this.setState({"errors": errors})
}

render() {
  return (
        <div className="accordion" id="shapeRequestForm">
            <div className="card" id="shapesCard">
                <div className="card-header" id="shapes">
                    <h2 className="mb-0">
                        <button className="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapseShapes" aria-expanded="true" aria-controls="collapseShapes">
                        Shapes
                        </button>
                    </h2>
                </div>
  
                <div id="collapseShapes" className="collapse show" aria-labelledby="shapes" data-parent="#shapeRequestForm">
                    <div className=" row card-body">
                        <div className="col-md-8 mr-3">
                            <ShapeDisplay id="shapeDisplay" setShapesState={this.setShapesState} setErrorsState={this.setErrorsState} shapes={this.state.shapes} errors={this.state.errors}/>
                        </div>
                        <div className="col-md-3 jumbotron float-right">
                            <ShapeGenerationForm setShapesState={this.setShapesState} setErrorsState={this.setErrorsState} errors={this.state.errors} shapes={this.state.shapes}/>
                        </div>
                    </div>
                </div>
            </div>
            <div className="card">
                <div className="card-header" id="algorithmParameters">
                    <AlgorithmParameterForm setOutputState={this.setOutputState} setShapesState={this.setShapesState} setErrorsState={this.setErrorsState} shapes={this.state.shapes} errors={this.state.errors} errors={this.state.errors}/>
                </div>
            </div>
        </div>
    )
  }
}
export default ShapeRequestForm