import React from 'react';
import './App.css';
import RouterPage from "./Router"
import {BrowserRouter} from "react-router-dom";

class App extends React.Component {
    constructor(props){
        super(props);
    }


    render() {
        return (
            <BrowserRouter>
                <div className="App">
                    <RouterPage />
                </div>
            </BrowserRouter>
        );
    }
}


export default App;