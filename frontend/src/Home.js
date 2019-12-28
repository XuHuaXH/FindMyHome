import React from "react";
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import RaisedButton from 'material-ui/RaisedButton';
import AppBar from "material-ui/AppBar";
import TextField from "material-ui/TextField";
import axios from 'axios';
import PropertyCard from "./PropertyCard"

class Home extends React.Component {

    constructor(props){
        super(props);
        this.state={
            displayedProperties:[],
            keyword:'',
            range:''
        }
    }

    componentWillMount(){
        let properties=[];
        // properties.push(<SearchBox parentContext={this} appContext={this.props.parentContext}/>);
        this.setState({
            displayedProperties: properties
        })
    }


    render() {
        return (
            <div>
                <MuiThemeProvider>
                    <div>
                        <AppBar
                            title="Search"
                        />
                        <TextField
                            hintText="Enter a keyword to search"
                            floatingLabelText="Keyword"
                            onChange = {(event,newValue) => this.setState({keyword:newValue})}
                        />
                        <br/>
                        <TextField
                            hintText="Enter a range in miles"
                            floatingLabelText="range"
                            onChange = {(event,newValue) => this.setState({range:newValue})}
                        />
                        <br/>
                        <RaisedButton label="Search" primary={true} style={style} onClick={(event) => this.handleClick(event)}/>
                    </div>
                </MuiThemeProvider>
                {this.state.displayedProperties}
            </div>
        );
    }

    handleClick(event){
        let url = "/search";

        axios.get(url + "?keyword=" + this.state.keyword + "&range=" + this.state.range)
            .then((response) => {
                console.log(response);
                if(response.status === 200){
                    console.log("Search successful");
                }
                else{
                    console.log("An error occurred");
                }

                // display the search results
                let properties = [];
                let numOfResults = response.data.length;
                for (let i = 0; i < numOfResults; ++i) {
                    let data = response.data[i];
                    properties.push(<PropertyCard
                        id={data.id}
                        price={data.price}
                        description={data.description}
                        streetNo={data.address.streetNo}
                        roadName={data.address.roadName}
                        city={data.address.city}
                        state={data.address.state}
                        zipCode={data.address.zipCode}
                    />);
                }
                this.setState({displayedProperties:properties});
            })
            .catch(function (error) {
                console.log(error);
            });

    }

}

const style = {
    margin: 15,
};
export default Home;