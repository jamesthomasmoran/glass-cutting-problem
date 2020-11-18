import React from "react";
import { render, unmountComponentAtNode } from "react-dom";
import { act,Simulate } from "react-dom/test-utils";
import AlgorithmParameterForm from "./algorithmParameterForm";
let container = null;

let shapes = []
    jest.mock('./handleApiCall'); // this happens automatically with automocking

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

it("should set error message if no cutting Algorithm selected", () => {
  let errors = {shapesGreaterMax: "", selectCuttingAlgorithm: ""}
    
  act(() => {
    
    let output = {}
    const shapeState = jest.fn((newShapes) => {shapes = newShapes})
    const errorState = jest.fn((updatedErrors) => {errors = updatedErrors})
    const outputState = jest.fn((updatedOutput) => {output = updatedOutput})
    render(<AlgorithmParameterForm setOutputState={outputState} setShapesState={shapeState} setErrorsState={errorState} shapes={shapes} errors={errors} />, container);
    let requestSubmit = document.querySelector("#submit-shape-request")   
        Simulate.submit(requestSubmit); 
  });
  expect(errors.selectCuttingAlgorithm).toBe("Must select a Cutting Algorithm")

})

