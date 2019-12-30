import React from "react";
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';


import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import Home from "./Home";
import Profile from "./Profile";
import Login from "./Login"
import Register from "./Register"
import PropertyDetails from "./PropertyDetails"
import Menu from "@material-ui/core/Menu"
import MenuItem from "@material-ui/core/MenuItem"


class RouterPage extends React.Component {
    constructor(props) {
        super(props);
        const isLoggedIn = localStorage.getItem("token") != null;
        this.state = {
            isLoggedIn: isLoggedIn
        }
    }


    logout = () => {
        localStorage.removeItem("token");
        this.setState({
            isLoggedIn : false
        });
    }

    login = () => {
        this.setState({
            isLoggedIn: true
        });
        console.log(this.state.isLoggedIn);
    }


    render() {

        const userActionButtons = [];
        if (this.state.isLoggedIn) {
            userActionButtons.push(
                <div style={{marginLeft: 'auto'}}>
                    <Button style={{display: "inline"}}>
                        <Link style={{
                            textDecoration: 'none',
                            color:'white'
                        }} to="/profile">Profile</Link>
                    </Button>
                    <Button style={{
                        textDecoration: 'none',
                        color: 'white'
                    }} onClick={() => this.logout()}>
                        Logout
                    </Button>
                </div>
            )

        } else {
            userActionButtons.push(
                <div style={{marginLeft: 'auto'}}>
                    <Button style={{display: "inline"}}>
                        <Link style={{
                            textDecoration: 'none',
                            color:'white'
                        }} to="/login">Login</Link>
                    </Button>
                    <Button style={{display: "inline"}}>
                        <Link style={{
                            textDecoration: 'none',
                            color:'white'
                        }} to="/register">Register</Link>
                    </Button>
                </div>
            )
        }



        return (
            <div>
                <Router>
                    <AppBar position="static">
                        <Toolbar>
                            <div>
                                <Button style={{display: "inline"}}>
                                    <Link style={{textDecoration: 'none', color:'white'}} to="/">Home</Link>
                                </Button>
                            </div>
                            {userActionButtons}
                        </Toolbar>
                    </AppBar>
                    <Switch>
                        <Route exact path="/">
                            <Home />
                        </Route>
                        <Route path="/login">
                            <Login loginAction={this.login}/>
                        </Route>
                        <Route path="/register">
                            <Register />
                        </Route>
                        <Route path="/profile">
                            <Profile />
                        </Route>
                        <Route path="/property-details/:id" component={PropertyDetails} />
                    </Switch>
                </Router>
            </div>
        );
    }
}

export default RouterPage;