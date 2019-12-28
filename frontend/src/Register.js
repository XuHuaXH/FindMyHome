import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import AppBar from 'material-ui/AppBar';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import axios from 'axios';

class Register extends Component {
    constructor(props){
        super(props);
        this.state={
            emailId:'',
            password:'',
            againPassword:''
        }
    }
    render() {
        return (
            <div>
                <MuiThemeProvider>
                    <div>
                        <AppBar
                            title="Register"
                        />
                        <br/>
                        <TextField
                            hintText="Enter your Email"
                            type="email"
                            floatingLabelText="Email"
                            onChange = {(event,newValue) => this.setState({emailId:newValue})}
                        />
                        <br/>
                        <TextField
                            type = "password"
                            hintText="Enter your Password"
                            floatingLabelText="Password"
                            onChange = {(event,newValue) => this.setState({password:newValue})}
                        />
                        <br/>
                        <TextField
                            type = "password"
                            hintText="Enter your Password Again"
                            floatingLabelText="Password Confirmation"
                            onChange = {(event,newValue) => this.setState({AgainPassword:newValue})}
                        />
                        <br/>
                        <RaisedButton label="Submit" primary={true} style={style} onClick={(event) => this.handleClick(event)}/>
                    </div>
                </MuiThemeProvider>
            </div>
        );
    }

    handleClick(event){
        let url = "/user/registration";
        console.log("values",this.state.emailId,this.state.password,this.state.AgainPassword);
        // TODO: check for empty values before hitting submit

        let payload={
            "emailId":this.state.emailId,
            "password":this.state.password,
            "againPassword":this.state.againPassword
        }
        axios.post(url, payload)
            .then(function (response) {
                console.log(response);
                if(response.status === 200){
                    console.log("registration successful");
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    }
}
const style = {
    margin: 15,
};
export default Register;

