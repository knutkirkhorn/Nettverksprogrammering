import React from 'react';
import { render } from 'react-dom'

export default class CustomerService {
  static instance=null;
  lastId=0;
  customers=[];

  // Return singleton
  static get() {
    if(!this.instance)
      this.instance=new CustomerService();
    return this.instance;
  }

  constructor() {
    this.customers.push({id: ++this.lastId, name: "Ola", city: "Trondheim"});
    this.customers.push({id: ++this.lastId, name: "Kari", city: "Oslo"});
    this.customers.push({id: ++this.lastId, name: "Per", city: "Tromsø"});
  }

  // Returns a manually created promise since we are later going to use fetch(),
  // which also returns a promise, to perform an http request.
  getCustomers() {
    return new Promise((resolve, reject)=>{
      var customer_id_and_names=[];
      for(var c=0;c<this.customers.length;c++) {
        customer_id_and_names.push({id: this.customers[c].id, name: this.customers[c].name});
      }
      resolve(customer_id_and_names);
    });
  }

  getCustomer(customerId) {
    return new Promise((resolve, reject)=>{
      for(var c=0;c<this.customers.length;c++) {
        if(this.customers[c].id==customerId) {
          resolve(this.customers[c]);
          return;
        }
      }
      reject("Customer not found");
    });
  }

  addCustomer(name, city) {
    return new Promise((resolve, reject)=>{
      if(name && city) {
        this.customers.push({id: ++this.lastId, name: name, city: city});
        resolve(this.lastId);
        return;
      }
      reject("name or city empty");
    });
  }

  deleteCustomer(customerId) {
    return new Promise((resolve, reject)=> {

      for (var i = 0; i < this.customers.length; i++) {
        if (this.customers[i].id == customerId) {
          this.customers.splice(i, 1);
          resolve(this.customers);
          return;
        }
      }
      reject("feil id");
    });
  }

  changeCustomerName(customerId, newName) {
    return new Promise((resolve, reject)=> {

      for (var i = 0; i < this.customers.length; i++) {
        if (this.customers[i].id == customerId) {
          this.customers[i].name = newName;
          resolve(this.customers[i]);
          return;
        }
      }
      reject("en feil skjedde");
    });
  }

  changeCustomerCity(customerId, newCityName) {
    return new Promise((resolve, reject)=> {

      for (var i = 0; i < this.customers.length; i++) {
        if (this.customers[i].id == customerId) {
          this.customers[i].city = newCityName;
          resolve(this.customers[i]);
          return;
        }
      }
    });
  }
}
