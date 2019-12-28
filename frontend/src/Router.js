import React from "react";
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


class RouterPage extends React.Component {
    constructor(props) {
        super(props);
    }



    render() {
        return (
            <Router>
                <div>
                    <ul>
                        <li>
                            <Link to="/">Home</Link>
                        </li>
                        <li>
                            <Link to="/login">Login</Link>
                        </li>
                        <li>
                            <Link to="/register">Register</Link>
                        </li>
                        <li>
                            <Link to="/profile">Profile</Link>
                        </li>
                    </ul>

                    <hr />

                    {/*
          A <Switch> looks through all its children <Route>
          elements and renders the first one whose path
          matches the current URL. Use a <Switch> any time
          you have multiple routes, but you want only one
          of them to render at a time
        */}
                    <Switch>
                        <Route exact path="/">
                            <Home />
                        </Route>
                        <Route path="/login">
                            <Login />
                        </Route>
                        <Route path="/register">
                            <Register />
                        </Route>
                        <Route path="/profile">
                            <Profile />
                        </Route>
                        <Route path="/property-details/:id" component={PropertyDetails} />
                    </Switch>
                </div>
            </Router>
        );
    }
}

export default RouterPage;