import React from "react";
import { render, unmountComponentAtNode } from "react-dom";
import { act, Simulate } from "react-dom/test-utils";

import ShapeRequestForm from "./shapeRequestForm";

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

it("should create a form accordion", () => {
    act(() => {
        render(<ShapeRequestForm />, container);
    });
    const accordion = document.querySelectorAll("#shapeRequestForm")
    expect(accordion.length).toBe(1)
  
  })

it("should create 2 cards", () => {
    act(() => {
        render(<ShapeRequestForm />, container);
    });
    const cards = document.querySelectorAll(".card")
    expect(cards.length).toBe(2)
  
  })
it("should create card 'shapes'", () => {
    act(() => {
        render(<ShapeRequestForm />, container);
    });
    const shapesCard = document.querySelectorAll("#shapesCard")
    expect(shapesCard.length).toBe(1)
  
  })

it("should create ShapeGenerationForm Component", () => {
    act(() => {
        render(<ShapeRequestForm />, container);
    });
    const shapeGenerationForm = document.querySelectorAll("#shapeGenerationForm")
    expect(shapeGenerationForm.length).toBe(1)
  
  })

  it("should create AlgorithmParameterForm Component", () => {
    act(() => {
        render(<ShapeRequestForm />, container);
    });
    const algorithmParameterForm = document.querySelectorAll("#algorithmParameterForm")
    expect(algorithmParameterForm.length).toBe(1)
  
  })