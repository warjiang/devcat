const path = require('path')
module.exports = {
    entry: {
        'demo1': ['./src/demo1.jsx'],
        'demo2': ['./src/demo2.jsx'],
    },
    module: {
        rules: [
            {
                test: /\.js.?/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['babel-preset-es2015', 'babel-preset-react']
                    }
                }
            },
            {
                test: /\.less/,
                exclude: /node_modules/,
                use: ['style-loader', 'css-loader', 'less-loader']
            }
        ]
    },
    output: {
        filename: '[name]-output.js',
        path: path.resolve(process.cwd(), './dist')
    }
}