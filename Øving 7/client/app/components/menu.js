import React from 'react';
import { render } from 'react-dom'

export default class Menu extends React.Component {
  render() {
    return <div className="menu"><a href="/#/">Customers</a>
    <a href="/#/about">About</a></div>;
  }
}
