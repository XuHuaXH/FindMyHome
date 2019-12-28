import React from 'react';
import './App.css';
import RouterPage from "./Router"
import TemporaryDrawer from "./Drawer"
import ButtonAppBar from "./AppBar"
import MainShowPage from "./Main"

class App extends React.Component {
    state = {
        "loginToken" : "",
        "displayState": "index",
        "drawerIsOpen": false
    };

    handleDrawerOpen = (value) => {
        this.setState(prevState => ({
            drawerIsOpen: !prevState.drawerIsOpen
        }))
    }

    handleSetMainState = (value) => {
        this.setState({"displayState": value})
    }

    handleSetToken = (value) => {
        this.setState({"loginToken": value})
    }



  render() {
    return (
        <div className="App">
          <ButtonAppBar handleDrawerOpen = {this.handleDrawerOpen} loginToken={this.state.loginToken} handleSetToken={this.handleSetToken} />
          <TemporaryDrawer drawerIsOpen = {this.state.drawerIsOpen} handleDrawerOpen = {this.handleDrawerOpen} handleSetMainState = {this.handleSetMainState}/>
          <MainShowPage PageType={this.state.displayState}/>
        </div>
    );
  }
}


export default App;