import "./shape.css"
function Shape(props) {
    return (
      <button  className={props.class} style={{height: props.height + "px", width : props.width + "px", left:props.x + "px", top:props.y+ "px"}}  onClick={props.onClick}>
        {props.value} 
      </button>
    );
  }

export default Shape;