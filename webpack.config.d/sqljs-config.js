// Taken from https://cashapp.github.io/sqldelight/2.0.0-rc02/js_sqlite/sqljs_worker/

config.resolve = {
  fallback: {
    fs: false,
    path: false,
    crypto: false,
  }
};

const CopyWebpackPlugin = require('copy-webpack-plugin');
config.plugins.push(
    new CopyWebpackPlugin({
      patterns: [
        '../../node_modules/sql.js/dist/sql-wasm.wasm'
      ]
    })
);

const path = require("path");
const os = require("os");
const dist = path.resolve("../../node_modules/sql.js/dist/")
const wasm = path.join(dist, "sql-wasm.wasm")

config.files.push({
  pattern: wasm,
  served: true,
  watched: false,
  included: false,
  nocache: false,
});

config.proxies["/sql-wasm.wasm"] = path.join("/absolute/", wasm)

// Adapted from: https://github.com/ryanclark/karma-webpack/issues/498#issuecomment-790040818
const output = {
  path: path.join(os.tmpdir(), '_karma_webpack_') + Math.floor(Math.random() * 1000000),
}
config.set({
  webpack: {...config.webpack, output}
});
config.files.push({
  pattern: `${output.path}/**/*`,
  watched: false,
  included: false,
});