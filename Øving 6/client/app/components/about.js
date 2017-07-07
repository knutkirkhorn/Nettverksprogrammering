import React from 'react';
import { render } from 'react-dom'
import { Router, Route, hashHistory } from 'react-router'

export default class About extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return <div className="container">
    <h3>About</h3><p>Hei, dette er en side for behandling av kunder.</p>
    </div>
  }
}
