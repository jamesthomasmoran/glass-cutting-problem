import React from 'react'
import '../../App.css';
import Sheet from '../Sheet/sheet'

class SheetDisplay extends React.Component {
    constructor(props) {
        super(props)
    }
    

  render(){
    return (
        <div class="mt-3 jumbotron xl-container">
           {this.props.output.sheets.length > 0 &&
            <div className="text-center d-inline">
                <div><span class="font-weight-bold">Algorithm:</span> {this.props.output.title}</div>
                <div><span class="font-weight-bold">Number of Sheets Used:</span> {this.props.output.numberOfSheetsUsed}</div>
                <div><span class="font-weight-bold">Area Usage Efficiency:</span> {this.props.output.areaUsageEfficiency}</div>
              
            </div>
            }
            <div className="d-inline text-center position-relative">
            {this.renderAllSheets(this.props.output.sheets)}
            </div>
        </div>
  )
  }

  renderSheet(shapes, number) {
    return (
      <Sheet
        shapes={shapes}
        number={number}
      />
    );
  }

  renderAllSheets(sheets){
    
    const renderedSheets = sheets.map((sheet, index) => (
      this.renderSheet(sheet.shapes, index + 1)
    )
    )
    return renderedSheets
    }

}
  export default SheetDisplay