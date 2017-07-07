import React from 'react';
import { render } from 'react-dom'

export default class CustomerService {
  static instance=null;

  // Return singleton
  static get() {
    if(!CustomerService.instance)
      CustomerService.instance=new CustomerService();
    return CustomerService.instance;
  }

  getCustomers() {
    return fetch("/customers").then((response)=>{
      if(!response.ok) {
        throw response.statusText;
      }
      return response.json();
    });
  }

  getCustomer(customerId) {
    return fetch("/customers/"+customerId).then((response)=>{
      if(!response.ok) {
        throw response.statusText;
      }
      return response.json();
    });
  }

  addCustomer(name, city) {
    var body=JSON.stringify({name: name, city: city});
    return fetch("/customers", {method: "POST", headers: new Headers({'Content-Type': 'application/json'}), body: body}).then((response)=>{
      if(!response.ok) {
        throw response.statusText;
      }
      return response.json();
    });
  }

  deleteCustomer(customerId) {
    var body = JSON.stringify({id: customerId});
    return fetch("/customers", {
      method: "DELETE",
      headers: new Headers({'Content-Type': 'application/json'}),
      body: body
    }).then((response) => {
      if (!response.ok) {
        throw response.statusText;
      }
      return response.json();
    });
  }

  changeCustomerName(customerId, newName) {
    var body = JSON.stringify({id: customerId, name: newName});
    return fetch("/customers/changeCustomerName", {
      method: "PUT",
      headers: new Headers({'Content-Type': 'application/json'}),
      body: body
    }).then((response) => {
      if (!response.ok) {
        throw response.statusText;
      }
      return response.json();
    });
  }

  changeCustomerCity(customerId, newCityName) {
    var body = JSON.stringify({id: customerId, city: newCityName});
    return fetch("/customers/changeCustomerCity", {
      method: "PUT",
      headers: new Headers({'Content-Type': 'application/json'}),
      body: body
    }).then((response) => {
      if (!response.ok) {
        throw response.statusText;
      }
      return response.json();
    });
  }
}
