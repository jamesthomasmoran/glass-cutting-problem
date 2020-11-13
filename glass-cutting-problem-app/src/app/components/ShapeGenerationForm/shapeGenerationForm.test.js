import React from "react";
import { render, unmountComponentAtNode } from "react-dom";
import { act, Simulate } from "react-dom/test-utils";

import ShapeGenerationForm from "./shapeGenerationForm";

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

it("should prevent width being set below 35", () => {
    let errors = {shapesGreaterMax: ""}
    let shapeList = []
    act(() => {
        const shapeState = jest.fn((newShapes) => {shapeList = newShapes})
        const errorState = jest.fn((updatedErrors) => {errors = updatedErrors})
        render(<ShapeGenerationForm setShapesState={shapeState} setErrorsState={errorState} errors={errors} shapes={shapeList}/>, container);
        let widthInput = document.querySelector("#widthInput")
        widthInput.value = "30"
        Simulate.change(widthInput);
    });
    const widthInput = document.querySelector("#widthInput")
    expect(widthInput.value).toBe("35")
  
  })

  it("should prevent width being set above 300", () => {
    let errors = {shapesGreaterMax: ""}
    let shapeList = []
    act(() => {
        const shapeState = jest.fn((newShapes) => {shapeList = newShapes})
        const errorState = jest.fn((updatedErrors) => {errors = updatedErrors})
        render(<ShapeGenerationForm setShapesState={shapeState} setErrorsState={errorState} errors={errors} shapes={shapeList}/>, container);
        let widthInput = document.querySelector("#widthInput")
        widthInput.value = "305"
        Simulate.change(widthInput);
    });
    const widthInput = document.querySelector("#widthInput")
    expect(widthInput.value).toBe("300")
  
  })

  it("should prevent height being set below 30", () => {
    let errors = {shapesGreaterMax: ""}
    let shapeList = []
    act(() => {
        const shapeState = jest.fn((newShapes) => {shapeList = newShapes})
        const errorState = jest.fn((updatedErrors) => {errors = updatedErrors})
        render(<ShapeGenerationForm setShapesState={shapeState} setErrorsState={errorState} errors={errors} shapes={shapeList}/>, container);
        let widthInput = document.querySelector("#heightInput")
        widthInput.value = "25"
        Simulate.change(widthInput);
    });
    const widthInput = document.querySelector("#heightInput")
    expect(widthInput.value).toBe("30")
  })

  it("should prevent height being set above 250", () => {
    let errors = {shapesGreaterMax: ""}
    let shapeList = []
    act(() => {
        const shapeState = jest.fn((newShapes) => {shapeList = newShapes})
        const errorState = jest.fn((updatedErrors) => {errors = updatedErrors})
        render(<ShapeGenerationForm setShapesState={shapeState} setErrorsState={errorState} errors={errors} shapes={shapeList}/>, container);
        let widthInput = document.querySelector("#heightInput")
        widthInput.value = "255"
        Simulate.change(widthInput);
    });
    const widthInput = document.querySelector("#heightInput")
    expect(widthInput.value).toBe("250")
  })

  it("should add shape To shape list on submit if shapelist length less than 50", () => {
    let errors = {shapesGreaterMax: ""}
    let shapeList = []
    act(() => {
        const shapeState = jest.fn((newShapes) => {shapeList = newShapes})
        const errorState = jest.fn((updatedErrors) => {errors = updatedErrors})
        render(<ShapeGenerationForm setShapesState={shapeState} setErrorsState={errorState} errors={errors} shapes={shapeList}/>, container);
        let submit = document.querySelector("#addShapeButton")
        Simulate.submit(submit);
    });
    
    expect(shapeList.length).toBe(1)
    expect(shapeList[0].width).toBe("35px")
    expect(shapeList[0].height).toBe("30px")
  })

  it("should not add shape and display error message when shapeList is already 50 items in length", () => {
    let errors = {shapesGreaterMax: ""}
    let shapeList = [{width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"35px", height:"30px"}, {width:"60px", height:"60px"}]
    act(() => {
        const shapeState = jest.fn((newShapes) => {shapeList = newShapes})
        const errorState = jest.fn((updatedErrors) => {errors = updatedErrors})
        render(<ShapeGenerationForm setShapesState={shapeState} setErrorsState={errorState} errors={errors} shapes={shapeList}/>, container);
        let submit = document.querySelector("#addShapeButton")
        Simulate.submit(submit);
    });

    expect(shapeList.length).toBe(50)
    expect(shapeList[49].width).toBe("60px")
    expect(shapeList[49].height).toBe("60px")
    expect(errors.shapesGreaterMax).toBe("You can only add 50 shapes")
  })