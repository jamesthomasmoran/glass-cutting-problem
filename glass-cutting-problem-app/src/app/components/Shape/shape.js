import "./shape.css"
function Shape(props) {
    return (
      <button  className={props.class} style={{height: props.height, width : props.width}}  onClick={props.onClick}>
        {props.value} 
      </button>
    );
  }

export default Shape;