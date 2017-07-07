// @flow

import React from 'react';
import { render } from 'react-dom'
import { Router, Route, hashHistory } from 'react-router'

//Importering av egne filer
import About from './components/about.js';
import Menu from './components/menu';
import CustomerService from './services/customer';
import CustomerListComponent from './components/customer-list';
import CustomerDetailsComponent from './components/customer-details';
//For importering: https://humaan.com/blog/getting-started-with-webpack-and-react-es6-style/

class Routes extends React.Component {
  render() {
    return <Router history={hashHistory}>
      <Route exact path="/" component={CustomerListComponent}/>
      <Route path="/customer/:customerId" component={CustomerDetailsComponent}/>
      <Route path="/about" component={About}/>
    </Router>;
  }
}

render(<div class="container">
  <Menu/>
  <Routes/>
</div>, document.getElementById('root'));
