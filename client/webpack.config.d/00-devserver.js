function InjectWebpackDevServerConfig(config){
    console.log("devServer: ", config.devServer);

    config.devServer = {
        ...config.devServer,
        historyApiFallback: true,
        client:{
            overlay: true,
            progress: true,
            logging: 'info',
        }
    }

}
(InjectWebpackDevServerConfig)(config);