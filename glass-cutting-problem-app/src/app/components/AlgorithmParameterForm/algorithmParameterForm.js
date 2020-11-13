import React from 'react'
import '../../App.css';

class AlgorithmParameterForm extends React.Component {


  render(){
      return(
          <div className="d-inline">
               
            <button className="btn btn-link text-left float-left">
                Fit Shapes On Sheets
            </button>
        
          
            <form>
                <input className="btn btn-primary align-right float-right ml-5" type="submit" value="Submit"/>
                <div className="d-inline float-right ml-4">
                    <label className="align-middle mr-1">Sorting:</label>
                    <div className="btn-group" data-toggle="buttons">
        
                        <label className="btn btn-primary">
                            <input type="radio"/> Desc
                        </label>
                        <label className="btn btn-primary">
                            <input type="radio"/> Asc
                        </label>
                    </div>
                </div>
                <div className="d-inline float-right ml-4">
                    <label className="align-middle mr-1">Algorithm:</label>
                    <div className="btn-group" data-toggle="buttons">
                        <label className="btn btn-primary">
                            <input type="radio"/> First Fit
                        </label>
                        <label className="btn btn-primary">
                            <input type="radio"/> Best Fit
                        </label>
                    </div>
                </div>
            </form>
           </div>
      )
  }
}
export default AlgorithmParameterForm