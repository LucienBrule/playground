import React from "react";
import ReactDOM from 'react-dom/client'
import {App} from "./app/";



function init(root: HTMLElement = document.getElementById("root") as HTMLElement){

    if(!root) throw new Error("No root element found");

    ReactDOM.createRoot(root).render(
        <React.StrictMode>
            <App/>
        </React.StrictMode>
    );
}

(window as any).init = init;
window.addEventListener("load", () => init());