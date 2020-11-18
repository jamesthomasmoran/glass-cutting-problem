import React from 'react'
import './App.css';
import ShapeRequestForm from './components/ShapeRequestForm/shapeRequestForm';

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
    <div className="container-xl">
        <ShapeRequestForm setOutputState={this.setOutputState}/>
  </div>
    )
  }
}
export default App

