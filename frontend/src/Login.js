import React from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import AppBar from 'material-ui/AppBar';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import axios from 'axios';
import { withRouter } from 'react-router-dom';

class Login extends React.Component {
    constructor(props){
        super(props);
        this.state={
            username:'',
            password:''
        }
    }

    render() {
        return (
            <div>
                <MuiThemeProvider>
                    <div>
                        <AppBar
                            title="Login"
                        />
                        <TextField
                            hintText="Enter your Username"
                            floatingLabelText="Username"
                            onChange = {(event,newValue) => this.setState({username:newValue})}
                        />
                        <br/>
                        <TextField
                            type="password"
                            hintText="Enter your Password"
                            floatingLabelText="Password"
                            onChange = {(event,newValue) => this.setState({password:newValue})}
                        />
                        <br/>
                        <RaisedButton label="Submit" primary={true} style={style} onClick={() => this.handleClick()}/>
                    </div>
                </MuiThemeProvider>
            </div>
        );
    }



    handleClick = () => {
        let url = "/login";
        let payload={
            "emailId":this.state.username,
            "password":this.state.password
        }
        axios.post(url, payload)
            .then((response) => {
                if(response.status === 200){
                    console.log("Login successful");
                    let token = response.headers.authorization;
                    localStorage.setItem('token', token);
                    this.props.loginAction();
                    this.props.history.push('/');
                }
            })
            .catch(function (error) {
                console.log(error);
                //TODO: add false message
            });
    }
}
const style = {
    margin: 15,
};
export default withRouter(Login);

