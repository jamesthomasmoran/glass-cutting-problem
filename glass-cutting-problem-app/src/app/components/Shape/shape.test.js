import React from "react";
import { render, unmountComponentAtNode } from "react-dom";
import { act } from "react-dom/test-utils";

import Shape from "./shape";

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

it("should set height to 50px", () => {
  act(() => {
    render(<Shape class="test-class" height="50" width="20" value="1" />, container);
  });
  const shapeButton = document.querySelector("button")
  expect(shapeButton.style.height).toBe("50px")

})

it("should set width to 20px", () => {
  act(() => {
    render(<Shape class="test-class" height="50" width="20" value="1" />, container);
  });
  const shapeButton = document.querySelector("button")
  expect(shapeButton.style.width).toBe("20px")

})

it("should set value to 1", () => {
  act(() => {
    render(<Shape class="test-class" height="50" width="20" value="1" />, container);
  });
  const shapeButton = document.querySelector("button")
  expect(shapeButton.textContent).toBe("1")

})

it("should set class to test-class", () => {
  act(() => {
    render(<Shape class="test-class" height="50" width="20" value="1" />, container);
  });
  const shapeButton = document.querySelector("button")
  expect(shapeButton.className).toBe("test-class")

})