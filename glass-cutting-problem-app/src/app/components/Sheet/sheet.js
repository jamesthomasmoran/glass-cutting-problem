import Shape from "../Shape/shape"
import "./sheet.css"
const shapeStyles = "border-1 border-white text-center position-absolute align-top"

function renderShape(width, height, insertionNumber, x, y) {
    return (
      <Shape
        width={width}
        height={height}
        x={x}
        y={y}
        class={shapeStyles}
        value={insertionNumber}
      />
    );
  }

function renderAllShapes(shapes){
    
    const renderedShapes = shapes.map((shape) => (
      renderShape(shape.width, shape.height, shape.insertionNumber,shape.x1, shape.y1)
    )
    )
    return renderedShapes
    }

function Sheet(props){
    return <div class="text-center m-4 d-inline-block">
      <h4>Sheet {props.number}</h4>
      <div class="bg-primary text-left sheet">
        {renderAllShapes(props.shapes)}
      </div>
    </div>
}
export default Sheet