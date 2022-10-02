import React from "react";
import {ViewRouter} from "~/routes";

// props for App class component
export interface IAppProps {
    children?: React.ReactNode;
}

// State for App class component
export interface IAppState {

}

// App class component with props
export class App extends React.Component<IAppProps, IAppState> {
    render() {
        return (
            <ViewRouter/>
            );
    }
}

