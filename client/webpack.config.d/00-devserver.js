function InjectWebpackDevServerConfig(config){
    console.log("devServer: ", config.devServer);

    config.devServer = {
        ...config.devServer,
        historyApiFallback: true,
        client:{
            overlay: true,
            progress: true,
            logging: 'info',
            webSocketURL: {
                hostname: 'localhost',
                pathname: '/ws',
                port: 80,
            }
        }
    }

}
(InjectWebpackDevServerConfig)(config);