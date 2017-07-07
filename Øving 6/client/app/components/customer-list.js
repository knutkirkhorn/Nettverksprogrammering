import React from 'react';
import { render } from 'react-dom'

//var CustomerService = require('./../services/customer.js');

import CustomerService from './../services/customer';

export default class CustomerListComponent extends React.Component {
  state={status: "", customers: [], newCustomerName: "", newCustomerCity: "", deleteCustomerID: ""}

  constructor() {
    super();

    CustomerService.get().getCustomers().then((result)=>{
      this.setState({status: "successfully loaded customer list", customers: result});
    }).catch((reason)=>{
      this.setState({status: "error: "+reason});
    });
  }

  // Event methods, which are called in render(), are declared as properties:
  onNewCustomerFormChanged = (event) => {
    this.setState({[event.target.name]: event.target.value});
  }

  // Event methods, which are called in render(), are declared as properties:
  onNewCustomer = (event) => {
    event.preventDefault();
    var name=this.state.newCustomerName;
    var city=this.state.newCustomerCity;
    CustomerService.get().addCustomer(name, city).then((result)=>{
      this.state.customers.push({id: result, name: name, city});
      this.setState({status: "successfully added new customer", customers: this.state.customers, newCustomerName: "", newCustomerCity: "", deleteCustomerID: ""});
    }).catch((reason)=>{
      this.setState({status: "error: "+reason});
    });
  }

  // Event method for deleting customer
  onDeleteCustomer = (event) => {
    event.preventDefault();
    var id = this.state.deleteCustomerID;
    CustomerService.get().deleteCustomer(id).then((result)=> {
      this.setState({status: "successfully deleted a customer with id: " + id, customers: result})
    }).catch((reason)=> {
      this.setState({status: "error: " + reason})
    });
  }

  render() {
    var listItems = this.state.customers.map((customer) =>
      <li className="user-box" key={customer.id}><a href={"/#/customer/"+customer.id}>{customer.name}</a></li>
    );
    return <div className="container">status: {this.state.status}<br/><ul>{listItems}</ul>
        <form onSubmit={this.onNewCustomer} onChange={this.onNewCustomerFormChanged}>
          <label>Name:<input type="text" name="newCustomerName" value={this.state.newCustomerName} /></label>
          <label>City:<input type="text" name="newCustomerCity" value={this.state.newCustomerCity} /></label>
          <input className="blue-button" type="submit" value="New Customer"/>
        </form>
        <form onSubmit={this.onDeleteCustomer} onChange={this.onNewCustomerFormChanged}>
          <p>Slett en bruker:</p>
          <label>Id: <input type="text" name="deleteCustomerID" value={this.state.deleteCustomerID}/></label>
          <input className="blue-button" type="submit" value="Delete Customer"/>
        </form>
      </div>
  }
}
