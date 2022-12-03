import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';

function main() {
    AppRegistry.registerComponent(appName, () => App);
}

export default main;
