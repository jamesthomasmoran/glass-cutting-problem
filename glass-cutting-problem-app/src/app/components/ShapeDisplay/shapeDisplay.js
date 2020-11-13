import React from 'react'
import '../../App.css';
import Shape from '../Shape/shape.js'

const shapeStyles = "alert-shape border-0 mr-1 mb-1 text-center d-inline align-top"
const empty = ""
class ShapeDisplay extends React.Component {
    constructor(props) {
        super(props);
        
        this.setErrorsState=this.props.setErrorsState.bind(this)
        this.setShapesState=this.props.setShapesState.bind(this)
      }

  render(){
    return this.renderAllShapes(this.props.shapes)
    
  }

  renderShape(i, width, height) {
    return (
      <Shape
        width={width}
        height={height}
        class={shapeStyles}
        value={i + 1}
        onClick={() => this.handleClick(i)}
      />
    );
  }

  renderAllShapes(shapes){
    
    const renderedShapes = shapes.map((shape, index) => (
      this.renderShape(index, shape.width, shape.height)
    )
    )
    return renderedShapes
    }

    handleClick(i) {
        let newShapes = this.props.shapes
      
        newShapes.splice(i, 1)
      
        this.setShapesState(newShapes)
      
        let updatedErrors = this.props.errors
        updatedErrors.shapesGreaterMax = empty
        this.setErrorsState(updatedErrors)
      }
}
  export default ShapeDisplay