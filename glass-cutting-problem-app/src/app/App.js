import React from 'react'
import './App.css';
import ShapeRequestForm from './components/ShapeRequestForm/shapeRequestForm';
import SheetDisplay from './components/SheetDisplay/sheetDisplay';

const empty = ""

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      output: {
        title: empty,
        numberOfSheetsUsed: empty,
        areaUsageEfficiency: empty,
        sheets: []
      }
    };
    this.setOutputState = this.setOutputState.bind(this)
  }
  
setOutputState(updatedTitle, updatedSheets, updatedNumberOfSheetsUsed, updatedAreaUsageEfficiency){
  let updatedOutput = this.state.output
  updatedOutput.title = updatedTitle
  updatedOutput.sheets = updatedSheets
  updatedOutput.numberOfSheetsUsed = updatedNumberOfSheetsUsed
  updatedOutput.areaUsageEfficiency = updatedAreaUsageEfficiency
  this.setState({"output": updatedOutput})
}

render() {
  return (
    <div>
      <div className="text-center navbar navbar-expand-lg navbar-light bg-light mb-3 pt-4">
      <h1 className="mr-auto ml-auto">Glass Cutting Problem</h1>
      </div>
      <div className="container-xl">
      
        <ShapeRequestForm setOutputState={this.setOutputState}/>
        <SheetDisplay output={this.state.output}></SheetDisplay>
  </div>
  </div>
    )
  }
}
export default App

