import React from 'react'
import '../../App.css';
import handleApiCall from './handleApiCall'

const empty = ""
const selectAlgorithmErrorMessage = "Must select a Cutting Algorithm"
class AlgorithmParameterForm extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            cuttingAlgorithm:empty,
            sorted:empty
        }
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleAlgorithmChange = this.handleAlgorithmChange.bind(this)
        this.handleSortedChange = this.handleSortedChange.bind(this)
        this.setErrorsState=this.props.setErrorsState.bind(this)
        this.setShapesState=this.props.setShapesState.bind(this)
        this.setOutputState=this.props.setOutputState.bind(this)
    }

    handleAlgorithmChange(event){
        this.state.cuttingAlgorithm = event.target.value
    }

    handleSortedChange(event){
        this.state.sorted = event.target.value
    }

    handleSubmit(event){
        let params = {}
        if(this.state.sorted != empty){
            params["sorted"] = this.state.sorted
        }
        if(this.state.cuttingAlgorithm != empty){
            params["cuttingAlgorithm"] = this.state.cuttingAlgorithm

            handleApiCall(params, this.props.shapes, this.props.setOutputState, this.props.setShapesState)
        }
        else{
            let updatedErrors = this.props.errors;

            updatedErrors.selectCuttingAlgorithm = selectAlgorithmErrorMessage
            this.setErrorsState(updatedErrors) 
        }
        event.preventDefault();
    }

  render(){
      return(
          <div id="algorithmParameterForm" className="d-inline">
               
            <button className="btn btn-link text-left float-left">
                Fit Shapes On Sheets
            </button>
        
          
            <form onSubmit={this.handleSubmit}>
                <input id="submit-shape-request" className="btn btn-primary align-right float-right ml-5" type="submit" value="Submit"/>
                <div className="d-inline float-right ml-4">
                    <label className="align-middle mr-1">Sorting:</label>
                    <div className="btn-group" data-toggle="buttons" onChange={this.handleSortedChange}>
        
                        <label className="btn btn-primary">
                            <input type="radio" value="desc"/> Desc
                        </label>
                        <label className="btn btn-primary">
                            <input type="radio" value="asc"/> Asc
                        </label>
                    </div>
                </div>
                <div className="d-inline float-right ml-4" >
                    <label className="align-middle mr-1">Algorithm:</label>
                    <div id="algorithm-input" className="btn-group" data-toggle="buttons" onChange={this.handleAlgorithmChange}>
                        <label className="btn btn-primary">
                            <input type="radio"value="firstFit"/> First Fit
                        </label>
                        <label className="btn btn-primary">
                            <input type="radio" value="bestFit"/> Best Fit
                        </label>
                    </div>
                </div>
                <input type="hidden" value={this.props.shapes}/>
            </form>
           </div>
      )
  }
}
export default AlgorithmParameterForm