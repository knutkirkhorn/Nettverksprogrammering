import React from 'react';
import { render } from 'react-dom'

import CustomerService from './../services/customer';

export default class CustomerDetailsComponent extends React.Component {
  state={status: "", customer: {}, newCustomerName: "", newCustomerCity: ""}

  constructor(props) {
    super(props);

    CustomerService.get().getCustomer(props.params.customerId).then((result)=>{
      this.setState({status: "successfully loaded customer details", customer: result});
    }).catch((reason)=>{
      this.setState({status: "error: "+reason});
    });
  }

  onFormsChanged = (event) => {
    this.setState({[event.target.name]: event.target.value});
  }

  onChangeCustomerName = (event) => {
    event.preventDefault();
    var id = this.state.customer.id;
    var newName = this.state.newCustomerName;

    CustomerService.get().changeCustomerName(id, newName).then((result) => {
      this.setState({status: "successfully updated customer name", customer: result});
    }).catch((reason)=> {
      this.setState({status: "error: " + reason});
    });
  }

  onChangeCustomerCity = (event) => {
    event.preventDefault();
    var id = this.state.customer.id;
    var newCityName = this.state.newCityName;

    CustomerService.get().changeCustomerCity(id, newCityName).then((result) => {
      this.setState({status: "successfully updated customer city", customer: result});
    }).catch((reason) => {
      this.setState({status: "error: " + reason});
    });
  }

  render() {
    return <div className="container">status: {this.state.status}<br/>
      <ul>
        <li>name: {this.state.customer.name}</li>
        <form onSubmit={this.onChangeCustomerName} onChange={this.onFormsChanged}>
          <br/>
          <b>Endre navn på kunde: </b><br/>
          <label>Nytt navn: <input type="text" name="newCustomerName" value={this.state.newCustomerName}/></label>
          <input className="blue-button" type="submit" value="Change customer name"/>
        </form>
        <br/>

        <li>city: {this.state.customer.city}</li>
        <form onSubmit={this.onChangeCustomerCity} onChange={this.onFormsChanged}>
          <br/>
          <b>Endre by på kunde: </b><br/>
          <label>Nytt bynavn: <input type="text" name="newCityName" value={this.state.newCityName}/></label>
          <input className="blue-button" type="submit" value="Change city name"/>
        </form>

      </ul>
    </div>
  }
}
