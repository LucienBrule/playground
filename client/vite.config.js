import {defineConfig} from 'vite'
import path from 'path'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
    resolve: {
        alias: {
            '~': path.resolve(__dirname, './src/main/webapp/'),
        }
    },
    server: {
        port: 3000,
    },
    plugins: [react(
        {
            babel: {
                parserOpts: {
                    plugins: [
                        'decorators-legacy', 'classProperties'
                    ]
                },
            },
        }
    )]
})
