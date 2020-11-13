import React from "react";
import { render, unmountComponentAtNode } from "react-dom";
import { act, Simulate } from "react-dom/test-utils";

import ShapeDisplay from "./shapeDisplay";

let container = null;
beforeEach(() => {
  // setup a DOM element as a render target
  container = document.createElement("div");
  document.body.appendChild(container);
});

afterEach(() => {
  // cleanup on exiting
  unmountComponentAtNode(container);
  container.remove();
  container = null;
});

it("should render 4 shapes", () => {
  let shapeList = [{"width": "40px", "height":"40px"}, {"width": "50px", "height":"40px"}, {"width": "40px", "height":"70px"},{"width": "50px", "height":"80px"}]
  let errors = {shapesGreaterThanMax: ""}
  act(() => {  
    const shapeState = jest.fn((newShapes) => {shapeList = newShapes})
    const errorState = jest.fn((updatedErrors) => {errors = updatedErrors})
    
    render(<ShapeDisplay setShapesState={shapeState} setErrorsState={errorState} shapes={shapeList} />, container);
  });
  const renderedShapes = document.querySelectorAll("button")

  expect(renderedShapes.length).toBe(4)
  
  expect(renderedShapes[0].style.width).toBe("40px")
  expect(renderedShapes[0].style.height).toBe("40px")
  expect(renderedShapes[0].textContent).toBe("1")

  expect(renderedShapes[1].style.width).toBe("50px")
  expect(renderedShapes[1].style.height).toBe("40px")
  expect(renderedShapes[1].textContent).toBe("2")

  expect(renderedShapes[2].style.width).toBe("40px")
  expect(renderedShapes[2].style.height).toBe("70px")
  expect(renderedShapes[2].textContent).toBe("3")

  expect(renderedShapes[3].style.width).toBe("50px")
  expect(renderedShapes[3].style.height).toBe("80px")
  expect(renderedShapes[3].textContent).toBe("4")
})

it("should render 0 shapes", () => {
    let shapeList = []
    let errors = {shapesGreaterThanMax: ""}
    act(() => {
      
        const shapeState = jest.fn((newShapes) => {shapeList = newShapes})
        const errorState = jest.fn((updatedErrors) => {errors = updatedErrors})
      
      render(<ShapeDisplay setShapesState={shapeState} setErrorsState={errorState} shapes={shapeList} />, container);
    });
    const renderedShapes = document.querySelectorAll("button")
  
    expect(renderedShapes.length).toBe(0)
    
  })

  it("should make shapeList length 3 when shape clicked on", () => {
    let shapeList = [{"width": "40px", "height":"40px"}, {"width": "50px", "height":"40px"}, {"width": "40px", "height":"70px"},{"width": "50px", "height":"80px"}]
    let errors = {shapesGreaterMax: ""}
    act(() => {
    
      const shapeState = jest.fn((newShapes) => {shapeList = newShapes})
      const errorState = jest.fn((updatedErrors) => {errors = updatedErrors})
      
      render(<ShapeDisplay setShapesState={shapeState} setErrorsState={errorState} shapes={shapeList} errors={errors}/>, container);
      const renderedShapes = document.querySelectorAll("button")
      Simulate.click(renderedShapes[0]) 
      render(<ShapeDisplay setShapesState={shapeState} setErrorsState={errorState} shapes={shapeList} errors={errors}/>, container);

    });
    const renderedShapesAfterClick = document.querySelectorAll("button")
    
    
    expect(renderedShapesAfterClick.length).toBe(3)
    
    expect(renderedShapesAfterClick[0].style.width).toBe("50px")
    expect(renderedShapesAfterClick[0].style.height).toBe("40px")
    expect(renderedShapesAfterClick[0].textContent).toBe("1")
  
    expect(renderedShapesAfterClick[1].style.width).toBe("40px")
    expect(renderedShapesAfterClick[1].style.height).toBe("70px")
    expect(renderedShapesAfterClick[1].textContent).toBe("2")
  
    expect(renderedShapesAfterClick[2].style.width).toBe("50px")
    expect(renderedShapesAfterClick[2].style.height).toBe("80px")
    expect(renderedShapesAfterClick[2].textContent).toBe("3")
  })

  it("should make error shapeGreaterMax empty on click", () => {
    let errors = {shapesGreaterMax: "not empty"}
    let shapeList = [{"width": "40px", "height":"40px"}, {"width": "50px", "height":"40px"}, {"width": "40px", "height":"70px"},{"width": "50px", "height":"80px"}]
    act(() => { 
      const shapeState = jest.fn((newShapes) => {shapeList = newShapes})
      const errorState = jest.fn((updatedErrors) => {errors = updatedErrors})
      
      render(<ShapeDisplay setShapesState={shapeState} setErrorsState={errorState} shapes={shapeList} errors={errors}/>, container);
      const renderedShapes = document.querySelectorAll("button")
      Simulate.click(renderedShapes[0]) 
      render(<ShapeDisplay setShapesState={shapeState} setErrorsState={errorState} shapes={shapeList} errors={errors}/>, container);

    });
    const renderedShapesAfterClick = document.querySelectorAll("button")
    
    expect(renderedShapesAfterClick.length).toBe(3)
    expect(errors.shapesGreaterMax).toBe("")
  })
