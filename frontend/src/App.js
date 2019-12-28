import React from 'react';
import './App.css';
import RouterPage from "./Router"

class App extends React.Component {
    constructor(props){
        super(props);
    }


    render() {
        return (
            <div className="App">
                <RouterPage />
            </div>
        );
    }
}


export default App;